package com.trendyol.pos.management.application;

import com.trendyol.payment.domain.model.PosSelection;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.PosDetail;
import com.trendyol.pos.management.domain.model.ProviderType;
import com.trendyol.pos.management.domain.service.PosDetailsService;
import com.trendyol.pos.management.infrastructure.service.PosService;
import org.springframework.stereotype.Service;

import java.util.List;

// <<Application Service>>
@Service
public class PosSelectionHandler {

    private final PosService posService;
    private final PosDetailsService posDetailsService;

    public PosSelectionHandler(PosService posService, PosDetailsService posDetailsService) {
        this.posService = posService;
        this.posDetailsService = posDetailsService;
    }

    public List<Pos> fetchValidList(PosSelection posSelection) {
        return this.posService.fetchValidList(posSelection);
    }

    public Pos findById(String id) {
        return this.posService.findById(id);
    }

    public Pos findByProviderType(ProviderType providerType) {
        return this.posService.findByProviderType(providerType);
    }

    public List<PosDetail> fetchDetailList(PosSelection posSelection) {
        List<Pos> posList = this.fetchValidList(posSelection);
        return this.posDetailsService.prepareList(posList);
    }
}
