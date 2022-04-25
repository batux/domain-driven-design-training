package com.trendyol.payment.application;

import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.service.ChargeAssembler;
import com.trendyol.payment.infrastructure.port.PosApiClient;
import com.trendyol.payment.infrastructure.service.ChargeService;
import com.trendyol.payment.infrastructure.service.PaymentService;

public abstract class ChargeHandler <T> {

    protected final ChargeAssembler chargeAssembler;
    protected final ChargeService chargeService;
    protected final PaymentService paymentService;
    protected final PosApiClient posApiClient;

    protected ChargeHandler(ChargeAssembler chargeAssembler, ChargeService chargeService, PaymentService paymentService, PosApiClient posApiClient) {
        this.chargeAssembler = chargeAssembler;
        this.chargeService = chargeService;
        this.paymentService = paymentService;
        this.posApiClient = posApiClient;
    }

    public T runChargeFlow(Charge chargeRequest) {
        Payment payment = this.chargeAssembler.assemble(chargeRequest);
        payment = this.paymentService.insert(payment);
        payment = charge(chargeRequest, payment);
        payment = this.paymentService.update(payment);
        return prepareChargeResult(payment);
    }

    public abstract Payment charge(Charge charge, Payment payment);
    public abstract T prepareChargeResult(Payment payment);
    public abstract boolean canExecute(Charge charge);
}
