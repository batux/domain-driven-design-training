package com.trendyol.post.payment.operation.domain.service;

import com.trendyol.post.payment.operation.domain.model.RefundRequest;
import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import org.springframework.stereotype.Component;

// <<Domain Service>>
@Component
public class RefundAssembler {

    public Refund assemble(CompletedPayment payment, RefundRequest refundRequest) {
        return new Refund(payment.getId(), refundRequest.getRefundAmount());
    }
}
