package com.trendyol.post.payment.operation.infrastructure.service;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.domain.model.transaction.ProviderTransactionDetail;
import com.trendyol.post.payment.operation.domain.model.transaction.Transaction;
import com.trendyol.post.payment.operation.infrastructure.port.ProviderAdapterDecider;
import com.trendyol.post.payment.operation.infrastructure.port.adapter.ProviderAdapter;
import org.springframework.stereotype.Service;

// <<Infrastructure Service>>
@Service
public class PostPaymentOperationService {

    private final ProviderAdapterDecider providerDecider;

    public PostPaymentOperationService(ProviderAdapterDecider providerDecider) {
        this.providerDecider = providerDecider;
    }

    public Transaction refund(Refund refund, Pos selectedPos) {
        ProviderAdapter<? extends ProviderTransactionDetail> providerAdapter = this.providerDecider.decide(selectedPos);
        ProviderTransactionDetail providerTransaction = providerAdapter.refund(refund, selectedPos);
        Transaction transaction = new Transaction();
        transaction.setTransactionDetail(providerTransaction);
        return transaction;
    }
}
