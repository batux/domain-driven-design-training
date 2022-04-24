package com.trendyol.payment.domain.model.charge.transaction;

// <<Value Object>>
public class SyncProviderTransactionDetail extends ProviderTransactionDetail {

    @Override
    public boolean isSuccess() {
        return "00".equals(super.getTransactionCode());
    }

    @Override
    public String accessProviderAction() {
        return super.getTransactionCode();
    }
}
