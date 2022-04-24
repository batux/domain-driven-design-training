package com.trendyol.payment.domain.model.charge.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

// <<Value Object>>
public class AsyncProviderTransactionDetail extends ProviderTransactionDetail {

    @Getter
    @Setter
    private String redirectionUrl;

    @Override
    public boolean isSuccess() {
        return !StringUtils.isEmpty(super.getTransactionCode()) &&
                !"00".equals(super.getTransactionCode());
    }

    @Override
    public String accessProviderAction() {
        return this.redirectionUrl;
    }
}
