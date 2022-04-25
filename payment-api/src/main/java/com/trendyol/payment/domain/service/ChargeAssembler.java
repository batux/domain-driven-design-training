package com.trendyol.payment.domain.service;

import com.trendyol.payment.domain.model.pos.PosSelection;
import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.CreditCard;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.Type;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// <<Domain Service>>
@Component
public class ChargeAssembler {

    public PosSelection preparePosSelection(Charge charge) {
        if(charge == null) {
            return null;
        }
        return new PosSelection(charge.getInstallmentCount(), determinePosType(charge.getType()));
    }

    private com.trendyol.payment.domain.model.pos.Type determinePosType(Type type) {
        if(Type.PAY_WITH_CARD.equals(type)) {
            return com.trendyol.payment.domain.model.pos.Type.SYNC;
        }
        else if(Type.PAY_ASYNC.equals(type)) {
            return com.trendyol.payment.domain.model.pos.Type.ASYNC;
        }
        throw new RuntimeException("Not suitable payment type!");
    }

    public Payment assemble(Charge charge) {

        if(StringUtils.isEmpty(charge.getReferenceNumber())) {
            return new Payment(
                    charge.getType(),
                    charge.getInstallmentCount(),
                    charge.getUser(),
                    charge.getAmount());
        }

        CreditCard card = new CreditCard(
                charge.getReferenceNumber(),
                charge.getHolderFullName(),
                charge.getMonth(),
                charge.getYear(),
                charge.getCvv(),
                charge.getFamily());

        return new Payment(
                charge.getType(),
                charge.getInstallmentCount(),
                charge.getUser(),
                charge.getAmount(), card);
    }
}
