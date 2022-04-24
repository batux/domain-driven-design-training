package com.trendyol.post.payment.operation.domain.model;

import com.trendyol.post.payment.operation.domain.model.refund.Money;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RefundRequest {
    @Getter
    private String paymentId;
    @Getter
    private Money refundAmount;
}
