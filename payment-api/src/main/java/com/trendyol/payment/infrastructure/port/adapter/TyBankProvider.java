package com.trendyol.payment.infrastructure.port.adapter;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.transaction.SyncProviderTransactionDetail;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.ProviderType;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class TyBankProvider extends ProviderAdapter<SyncProviderTransactionDetail> {

    protected TyBankProvider() {
        super(ProviderType.TY_BANK);
    }

    @Override
    public SyncProviderTransactionDetail charge(Payment payment, Pos selectedPos) {
        return null;
    }
}
