package com.trendyol.post.payment.operation.domain.model.transaction;

// <<Value Object>>
public class SyncProviderTransactionDetail extends ProviderTransactionDetail {

    @Override
    public boolean isSuccess() {
        return "00".equals(super.getTransactionCode());
    }

    @Override
    public boolean isForwarded() {
        return false;
    }
}
