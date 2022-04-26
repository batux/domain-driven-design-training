package com.trendyol.payment.application;

import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.Type;
import com.trendyol.payment.domain.model.charge.event.Accepted;
import com.trendyol.payment.domain.model.charge.transaction.Transaction;
import com.trendyol.payment.domain.model.pos.PosDetail;
import com.trendyol.payment.domain.service.ChargeAssembler;
import com.trendyol.payment.infrastructure.port.PosApiClient;
import com.trendyol.payment.infrastructure.service.ChargeService;
import com.trendyol.payment.infrastructure.service.PaymentService;
import org.springframework.stereotype.Service;

// <<Application Service>>
@Service
public class AsyncChargeHandler extends ChargeHandler<Accepted> {

    public AsyncChargeHandler(ChargeAssembler assembler, ChargeService chargeService, PaymentService paymentService, PosApiClient posApiClient) {
        super(assembler, chargeService, paymentService, posApiClient);
    }

    @Override
    public Payment charge(Charge charge, Payment payment) {
        PosDetail selectedPos = this.posApiClient.findByProviderType(charge.getProviderType());
        Transaction transaction = chargeService.charge(payment, selectedPos);
        payment.addTransaction(transaction);
        return payment;
    }

    @Override
    public Accepted prepareChargeResult(Payment payment) {
        return payment.accepted();
    }

    @Override
    public boolean canExecute(Charge charge) {
        return Type.PAY_ASYNC.equals(charge.getType());
    }
}
