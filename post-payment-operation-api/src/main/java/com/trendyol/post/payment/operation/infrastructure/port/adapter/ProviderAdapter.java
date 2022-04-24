package com.trendyol.post.payment.operation.infrastructure.port.adapter;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import com.trendyol.post.payment.operation.domain.model.pos.ProviderType;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.model.transaction.ProviderTransactionDetail;

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

    public abstract T refund(Refund refund, Pos selectedPos);
}
