package com.trendyol.post.payment.operation.infrastructure.port.adapter;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import com.trendyol.post.payment.operation.domain.model.pos.ProviderType;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.model.transaction.SyncProviderTransactionDetail;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class TyBankProvider extends ProviderAdapter<SyncProviderTransactionDetail> {

    protected TyBankProvider() {
        super(ProviderType.TY_BANK);
    }

    @Override
    public SyncProviderTransactionDetail refund(Refund refund, Pos selectedPos) {
        return null;
    }
}
