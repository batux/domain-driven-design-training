package com.trendyol.post.payment.operation.application.model;

import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefundContext {

    private CompletedPayment payment;
    private Refund refund;
}
