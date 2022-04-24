package com.trendyol.payment.domain.model.charge;

import com.trendyol.payment.domain.model.Customer;
import com.trendyol.payment.domain.model.Money;
import com.trendyol.pos.management.domain.model.ProviderType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Charge {

    private String referenceNumber;
    private String holderFullName;
    private String cvv;
    private int month;
    private int year;
    private int installmentCount;

    private Family family;
    private Type type;
    private ProviderType providerType;
    private Money amount;
    private Customer user;
}
