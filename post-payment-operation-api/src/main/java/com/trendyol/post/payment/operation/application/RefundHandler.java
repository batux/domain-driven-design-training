package com.trendyol.post.payment.operation.application;

import com.trendyol.post.payment.operation.application.model.RefundContext;
import com.trendyol.post.payment.operation.domain.model.RefundRequest;
import com.trendyol.post.payment.operation.domain.model.event.Refunded;
import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.service.RefundedProducer;
import com.trendyol.post.payment.operation.infrastructure.port.PosApiClient;
import com.trendyol.post.payment.operation.infrastructure.service.PostPaymentOperationService;
import com.trendyol.post.payment.operation.infrastructure.service.RefundService;
import org.springframework.stereotype.Service;

// <<Application Service>>
@Service
public class RefundHandler {

    private final RefundPreparationService refundPreparationService;
    private final PosApiClient posApiAdapter;
    private final RefundService refundService;
    private final PostPaymentOperationService ppoService;

    public RefundHandler(RefundPreparationService refundPreparationService, PosApiClient posApiAdapter, RefundService refundService, PostPaymentOperationService ppoService) {
        this.refundPreparationService = refundPreparationService;
        this.posApiAdapter = posApiAdapter;
        this.refundService = refundService;
        this.ppoService = ppoService;
    }

    public Refunded runRefundFlow(RefundRequest refundRequest) {

        Refund refund = null;
        if(refundRequest.hasRefundReferenceNumber()) {
            refund = this.refundService.findById(refundRequest.getRefundReferenceNumber());
        }
        if(refund != null && refund.canRefund()) {
            return RefundedProducer.assemble(refund);
        }

        RefundContext refundContext = this.refundPreparationService.prepare(refundRequest);
        refund = refundContext.getRefund();
        CompletedPayment payment = refundContext.getPayment();

        // insert refund to db (infrastructure service)
        refund = this.refundService.insert(refund);

        // fetch PosDetail info from pos-api (infrastructure service)
        Pos selectedPos = this.posApiAdapter.fetchById(payment.getPosId());
        if(selectedPos == null) {
            throw new RuntimeException("Valid POS can not be found!");
        }

        // call refund method to make refund ops. PostPaymentOperationService (infrastructure service)
        this.ppoService.refund(refund, selectedPos);

        // update refund entity (infrastructure service)
        this.refundService.update(refund);

        // prepare refunded response (domain service)
        return RefundedProducer.assemble(refund);
    }
}
