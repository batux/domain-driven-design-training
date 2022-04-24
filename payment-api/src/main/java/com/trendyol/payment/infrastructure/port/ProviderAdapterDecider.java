package com.trendyol.payment.infrastructure.port;

import com.trendyol.payment.domain.model.charge.transaction.ProviderTransactionDetail;
import com.trendyol.payment.infrastructure.port.adapter.ProviderAdapter;
import com.trendyol.pos.management.domain.model.Pos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

// <<Infrastructure Service>>
@Component
public class ProviderAdapterDecider {

    private final List<ProviderAdapter<? extends ProviderTransactionDetail>> adapters;

    public ProviderAdapterDecider(List<ProviderAdapter<? extends ProviderTransactionDetail>> adapters) {
        this.adapters = adapters;
    }

    public ProviderAdapter<? extends ProviderTransactionDetail> decide(Pos pos) {
        Optional<ProviderAdapter<? extends ProviderTransactionDetail>> adapterOption = this.adapters.stream().filter(adapter -> adapter.canExecute(pos)).findFirst();
        if(adapterOption.isEmpty()) {
            throw new RuntimeException("Not found any defined virtual pos!");
        }
        return adapterOption.get();
    }
}
