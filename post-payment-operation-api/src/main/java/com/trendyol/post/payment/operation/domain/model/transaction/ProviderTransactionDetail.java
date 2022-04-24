package com.trendyol.post.payment.operation.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class ProviderTransactionDetail {

    @Getter
    private String transactionCode;
    @Getter
    private String message;

    public abstract boolean isSuccess();
    public abstract boolean isForwarded();
}
