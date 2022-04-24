package com.trendyol.post.payment.operation.infrastructure.port.adapter;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import com.trendyol.post.payment.operation.domain.model.pos.ProviderType;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.model.transaction.AsyncProviderTransactionDetail;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class AdyenProviderAdapter extends ProviderAdapter<AsyncProviderTransactionDetail> {

    public AdyenProviderAdapter() {
        super(ProviderType.ADYEN);
    }

    @Override
    public AsyncProviderTransactionDetail refund(Refund refund, Pos selectedPos) {
        return null;
    }
}
