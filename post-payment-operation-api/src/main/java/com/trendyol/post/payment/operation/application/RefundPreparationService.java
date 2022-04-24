package com.trendyol.post.payment.operation.application;

import com.trendyol.post.payment.operation.application.model.RefundContext;
import com.trendyol.post.payment.operation.domain.model.RefundRequest;
import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.service.RefundAssembler;
import com.trendyol.post.payment.operation.domain.service.RefundableOperationChecker;
import com.trendyol.post.payment.operation.infrastructure.port.PaymentApiClient;
import org.springframework.stereotype.Service;

@Service
public class RefundPreparationService {

    private final PaymentApiClient paymentApiAdapter;
    private final RefundableOperationChecker refundableOperationChecker;
    private final RefundAssembler refundAssembler;

    public RefundPreparationService(PaymentApiClient paymentApiAdapter, RefundableOperationChecker refundableOperationChecker, RefundAssembler refundAssembler) {
        this.paymentApiAdapter = paymentApiAdapter;
        this.refundableOperationChecker = refundableOperationChecker;
        this.refundAssembler = refundAssembler;
    }

    public RefundContext prepare(RefundRequest refundRequest) {
        // fetch CompletedPayment record from payment-api (infrastructure service)
        CompletedPayment payment = this.paymentApiAdapter.fetchById(refundRequest.getPaymentId());
        if(payment == null) {
            throw new RuntimeException("Payment record not found!");
        }

        // RefundRequest money and Payment Money check (domain service)
        this.refundableOperationChecker.check(payment, refundRequest);

        // prepare refund entity (domain service)
        return new RefundContext(payment, this.refundAssembler.assemble(payment, refundRequest));
    }
}
