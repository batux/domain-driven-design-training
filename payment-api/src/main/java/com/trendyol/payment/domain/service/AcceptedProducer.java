package com.trendyol.payment.domain.service;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.domain.model.charge.event.Accepted;
import com.trendyol.payment.domain.model.charge.event.Authorised;

public class AcceptedProducer {
    public static Accepted assemble(Payment payment) {
        return new Accepted(payment.getId(), payment.getStatus(), payment.isSuccessful(), payment.accessProviderAction());
    }
}
