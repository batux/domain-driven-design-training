package com.trendyol.payment.infrastructure.service;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.transaction.ProviderTransactionDetail;
import com.trendyol.payment.domain.model.charge.transaction.Transaction;
import com.trendyol.payment.infrastructure.port.adapter.ProviderAdapter;
import com.trendyol.payment.infrastructure.port.ProviderAdapterDecider;
import com.trendyol.pos.management.domain.model.Pos;
import org.springframework.stereotype.Service;

// <<Infrastructure Service>>
@Service
public class ChargeService {

    private final ProviderAdapterDecider providerAdapterDecider;

    public ChargeService(ProviderAdapterDecider providerAdapterDecider) {
        this.providerAdapterDecider = providerAdapterDecider;
    }

    public Transaction charge(Payment payment, Pos selectedPos) {

        ProviderAdapter<? extends ProviderTransactionDetail> providerAdapter = providerAdapterDecider.decide(selectedPos);
        ProviderTransactionDetail providerTransaction = providerAdapter.charge(payment, selectedPos);
        Transaction transaction = new Transaction(selectedPos.buildDetails());
        transaction.setTransactionDetail(providerTransaction);
        return transaction;
    }
}
