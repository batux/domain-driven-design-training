package com.trendyol.payment.infrastructure.port.adapter;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.transaction.ProviderTransactionDetail;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.ProviderType;

public abstract class ProviderAdapter <T extends ProviderTransactionDetail> {

    private final ProviderType providerType;

    protected ProviderAdapter(ProviderType providerType) {
        this.providerType = providerType;
    }

    public boolean canExecute(Pos pos) {
        return this.isSame(pos.getProviderType());
    }

    private boolean isSame(ProviderType providerType) {
        return this.providerType.equals(providerType);
    }

    public abstract T charge(Payment payment, Pos selectedPos);
}
