package com.trendyol.post.payment.operation.domain.model;

import com.trendyol.post.payment.operation.domain.model.refund.Money;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RefundRequest {
    @Getter
    private String paymentId;
    @Getter
    private Money refundAmount;
    @Getter
    private String refundReferenceNumber;

    public boolean hasRefundReferenceNumber() {
        return !StringUtils.isEmpty(this.refundReferenceNumber);
    }
}
