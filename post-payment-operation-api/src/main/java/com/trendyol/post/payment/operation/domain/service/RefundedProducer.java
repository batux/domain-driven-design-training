package com.trendyol.post.payment.operation.domain.service;

import com.trendyol.post.payment.operation.domain.model.event.Refunded;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;

public class RefundedProducer {

    public static Refunded assemble(Refund refund) {
        return new Refunded(refund.getId(), refund.getPaymentId(), refund.getAmount(), refund.isForwarded());
    }
}
