package com.trendyol.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {

    @Getter
    private Long id;
    @Getter
    private String email;
    @Getter
    private String phoneNumber;
}
