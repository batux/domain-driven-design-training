package com.trendyol.pos.management.infrastructure.repository;

import com.trendyol.payment.domain.model.PosSelection;
import com.trendyol.pos.management.domain.model.Pos;
import com.trendyol.pos.management.domain.model.ProviderType;

import java.util.Arrays;
import java.util.List;

// <<Repository>>
public class PosRepository {

    public Pos save(Pos pos) {
        // save in db
        return pos;
    }

    public Pos update(Pos pos) {
        // update in db
        return pos;
    }

    public boolean delete(Pos pos) {
        // delete in db
        return true;
    }

    public Pos findById(String id) {
        // select from db
        return null;
    }

    public Pos findByProviderType(ProviderType providerType) {
        return null;
    }

    public List<Pos> fetchValidList(PosSelection posSelection) {
        return Arrays.asList();
    }
}
