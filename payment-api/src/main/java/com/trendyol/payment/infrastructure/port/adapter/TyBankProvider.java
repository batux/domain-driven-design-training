package com.trendyol.payment.infrastructure.port.adapter;

import com.trendyol.payment.domain.model.pos.PosDetail;
import com.trendyol.payment.domain.model.pos.ProviderType;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.transaction.SyncProviderTransactionDetail;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class TyBankProvider extends ProviderAdapter<SyncProviderTransactionDetail> {

    protected TyBankProvider() {
        super(ProviderType.TY_BANK);
    }

    @Override
    public SyncProviderTransactionDetail charge(Payment payment, PosDetail selectedPos) {
        return null;
    }
}
