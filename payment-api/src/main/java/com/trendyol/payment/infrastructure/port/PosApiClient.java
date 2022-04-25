package com.trendyol.payment.infrastructure.port;

import com.trendyol.payment.domain.model.pos.PosDetail;
import com.trendyol.payment.domain.model.pos.PosSelection;
import com.trendyol.payment.domain.model.pos.ProviderType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PosApiClient {

    public PosDetail findByProviderType(ProviderType providerType) {
        return new PosDetail();
    }

    public List<PosDetail> fetchValidList(PosSelection posSelection) {
        return Collections.emptyList();
    }
}
