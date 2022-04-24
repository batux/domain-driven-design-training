package com.trendyol.post.payment.operation.domain.model.event;

import com.trendyol.post.payment.operation.domain.model.refund.Money;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Refunded {
    @Getter
    private String refundId;
    @Getter
    private String paymentId;
    @Getter
    private Money refundAmount;
    private boolean forwarded;

    public boolean isForwarded() {
        return this.forwarded;
    }
}
