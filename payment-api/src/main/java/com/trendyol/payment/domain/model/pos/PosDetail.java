package com.trendyol.payment.domain.model.pos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PosDetail {

    @Getter
    private String id;
    @Getter
    private Type type;
    @Getter
    private ProviderType providerType;
    @Getter
    private Secret secrets;
    @Getter
    private Url urls;
}
