package com.trendyol.payment.domain.model;

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

    public Money add(Money amount) {
        if(amount == null) {
            return this;
        }
        BigDecimal newAmount = new BigDecimal("0");
        newAmount = newAmount.setScale(2);
        newAmount = newAmount.add(this.amount);
        newAmount = newAmount.add(amount.amount);
        return new Money(newAmount, this.currency);
    }

    public Money withDraw(Money amount) {
        if(amount == null) {
            return this;
        }
        BigDecimal newAmount = new BigDecimal("0");
        newAmount = newAmount.setScale(2);
        newAmount = newAmount.add(this.amount);
        newAmount = newAmount.subtract(amount.amount);
        return new Money(newAmount, this.currency);
    }
}
