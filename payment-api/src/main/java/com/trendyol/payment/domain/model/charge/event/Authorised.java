package com.trendyol.payment.domain.model.charge.event;

import com.trendyol.payment.domain.model.charge.Status;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Authorised {

    @Getter
    private String paymentId;
    @Getter
    private Status paymentStatus;
    @Getter
    private Boolean transactionStatus;
}
