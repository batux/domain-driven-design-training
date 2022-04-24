package com.trendyol.payment.domain.model.charge.event;

import com.trendyol.payment.domain.model.charge.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

// <<Value Object>>
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Accepted extends Authorised {

    @Getter
    private String redirectionUrl;

    @Builder
    public Accepted(String paymentId, Status paymentStatus, Boolean transactionStatus, String redirectionUrl) {
        super(paymentId, paymentStatus, transactionStatus);
        this.redirectionUrl = redirectionUrl;
    }
}
