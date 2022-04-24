package com.trendyol.post.payment.operation.domain.model.transaction;

import org.springframework.util.StringUtils;

// <<Value Object>>
public class AsyncProviderTransactionDetail extends ProviderTransactionDetail {

    @Override
    public boolean isSuccess() {
        return !StringUtils.isEmpty(super.getTransactionCode()) &&
                !"00".equals(super.getTransactionCode());
    }

    @Override
    public boolean isForwarded() {
        return true;
    }
}
