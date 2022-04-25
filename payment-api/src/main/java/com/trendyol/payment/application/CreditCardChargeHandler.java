package com.trendyol.payment.application;

import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.Type;
import com.trendyol.payment.domain.model.charge.event.Authorised;
import com.trendyol.payment.domain.model.charge.transaction.Transaction;
import com.trendyol.payment.domain.model.pos.PosDetail;
import com.trendyol.payment.domain.service.AuthorisedProducer;
import com.trendyol.payment.domain.service.ChargeAssembler;
import com.trendyol.payment.infrastructure.port.PosApiClient;
import com.trendyol.payment.infrastructure.service.ChargeService;
import com.trendyol.payment.infrastructure.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

// <<Application Service>>
@Service
public class CreditCardChargeHandler extends ChargeHandler<Authorised> {

    public CreditCardChargeHandler(ChargeAssembler assembler, ChargeService chargeService, PaymentService paymentService, PosApiClient posApiClient) {
        super(assembler, chargeService, paymentService, posApiClient);
    }

    @Override
    public Payment charge(Charge charge, Payment payment) {
        // INFO: pos retry logic is special for credit card charge flow
        List<PosDetail> posList = this.posApiClient.fetchValidList(this.chargeAssembler.preparePosSelection(charge));
        Iterator<PosDetail> posIterator = posList.iterator();
        do {
            PosDetail selectedPos = posIterator.next();
            Transaction transaction = chargeService.charge(payment, selectedPos);
            payment.addTransaction(transaction);
        } while(!payment.isSuccessful() && posIterator.hasNext());
        return payment;
    }

    @Override
    public Authorised prepareChargeResult(Payment payment) {
        return AuthorisedProducer.assemble(payment);
    }

    @Override
    public boolean canExecute(Charge charge) {
        return Type.PAY_WITH_CARD.equals(charge.getType());
    }
}
