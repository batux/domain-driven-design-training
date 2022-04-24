package com.trendyol.post.payment.operation.domain.model.refund;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Money {
    private BigDecimal amount;
    private Currency currency;

    public boolean isValid() {
        return this.amount != null && this.amount.doubleValue() > 0;
    }

    public boolean isGreaterThan(Money amount) {
        if(this.isValid()) {
            return false;
        }
        if(amount == null) {
            return false;
        }
        return this.amount.doubleValue() > amount.amount.doubleValue();
    }

    public boolean isLowerThan(Money amount) {
        if(this.isValid()) {
            return false;
        }
        if(amount == null) {
            return false;
        }
        return this.amount.doubleValue() < amount.amount.doubleValue();
    }
}
