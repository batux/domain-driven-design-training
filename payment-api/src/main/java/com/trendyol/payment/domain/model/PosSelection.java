package com.trendyol.payment.domain.model;

import com.trendyol.pos.management.domain.model.Type;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PosSelection {

    @Getter
    private int installmentCount;
    @Getter
    private Type type;
}
