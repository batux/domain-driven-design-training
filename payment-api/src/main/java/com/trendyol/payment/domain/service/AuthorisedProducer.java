package com.trendyol.payment.domain.service;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.event.Authorised;

public class AuthorisedProducer {
    public static Authorised assemble(Payment payment) {
        return new Authorised(payment.getId(), payment.getStatus(), payment.isSuccessful());
    }
}
