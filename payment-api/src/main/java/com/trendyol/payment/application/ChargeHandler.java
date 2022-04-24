package com.trendyol.payment.application;

import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.service.ChargeAssembler;
import com.trendyol.payment.infrastructure.service.ChargeService;
import com.trendyol.payment.infrastructure.service.PaymentService;
import com.trendyol.pos.management.application.PosSelectionHandler;

public abstract class ChargeHandler <T> {

    protected final ChargeAssembler chargeAssembler;
    protected final ChargeService chargeService;
    protected final PaymentService paymentService;
    protected final PosSelectionHandler posSelectionHandler;

    protected ChargeHandler(ChargeAssembler chargeAssembler, ChargeService chargeService, PaymentService paymentService, PosSelectionHandler posSelectionHandler) {
        this.chargeAssembler = chargeAssembler;
        this.chargeService = chargeService;
        this.paymentService = paymentService;
        this.posSelectionHandler = posSelectionHandler;
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
