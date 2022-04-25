package com.trendyol.payment.infrastructure.port.adapter;

import com.trendyol.payment.domain.model.pos.PosDetail;
import com.trendyol.payment.domain.model.pos.ProviderType;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.transaction.AsyncProviderTransactionDetail;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class AdyenProviderAdapter extends ProviderAdapter<AsyncProviderTransactionDetail> {

    public AdyenProviderAdapter() {
        super(ProviderType.ADYEN);
    }

    @Override
    public AsyncProviderTransactionDetail charge(Payment payment, PosDetail selectedPos) {
        return null;
    }
}
