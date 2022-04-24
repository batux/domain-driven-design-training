package com.trendyol.payment.domain.model.charge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Date;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreditCard {

    @Getter
    private String referenceNumber;
    @Getter
    private String holderFullName;
    @Getter
    private int month;
    @Getter
    private int year;
    @Getter
    private String cvv;
    @Getter
    private Family family;

    public boolean isValid() {
        return !StringUtils.isEmpty(this.referenceNumber) &&
                !StringUtils.isEmpty(this.holderFullName) &&
                !StringUtils.isEmpty(this.cvv) &&
                this.month > 0 && this.month <= 12 &&
                this.year >= (new Date()).getYear() &&
                this.checkWithLuhnAlgorithm();
    }

    public boolean isSameFamily(CreditCard card) {
        if(card == null || this.family == null) {
            return false;
        }
        return this.family.equals(card.getFamily());
    }

    private boolean checkWithLuhnAlgorithm() {
        if(!StringUtils.isEmpty(this.referenceNumber)) {
            return false;
        }

        int nDigits = this.referenceNumber.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = referenceNumber.charAt(i) - '0';

            if (isSecond) {
                d = d * 2;
            }
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
