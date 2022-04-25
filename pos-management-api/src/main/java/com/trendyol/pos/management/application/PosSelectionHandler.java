package com.trendyol.pos.management.application;

import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.PosDetail;
import com.trendyol.pos.management.domain.model.PosSelection;
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

    public List<PosDetail> fetchValidList(PosSelection posSelection) {
        List<Pos> posList = this.posService.fetchValidList(posSelection);
        return this.posDetailsService.prepareList(posList);
    }

    public PosDetail findById(String id) {
        Pos pos = this.posService.findById(id);
        if(pos == null) {
            throw new RuntimeException("Pos is not valid!");
        }
        return pos.buildDetails();
    }

    public PosDetail findByProviderType(ProviderType providerType) {
        Pos pos = this.posService.findByProviderType(providerType);
        if(pos == null) {
            throw new RuntimeException("Pos is not valid!");
        }
        return pos.buildDetails();
    }
}
