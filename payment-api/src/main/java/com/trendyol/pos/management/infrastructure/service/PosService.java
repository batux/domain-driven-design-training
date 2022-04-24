package com.trendyol.pos.management.infrastructure.service;

import com.trendyol.payment.domain.model.PosSelection;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.ProviderType;
import com.trendyol.pos.management.infrastructure.repository.PosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// <<Infrastructure Service>>
@Service
public class PosService {

    private final PosRepository repository;

    public PosService(PosRepository repository) {
        this.repository = repository;
    }

    public List<Pos> fetchValidList(PosSelection posSelection) {
        return this.repository.fetchValidList(posSelection);
    }

    public Pos findById(String id) {
        return this.repository.findById(id);
    }

    public Pos findByProviderType(ProviderType providerType) {
        return this.repository.findByProviderType(providerType);
    }
}
