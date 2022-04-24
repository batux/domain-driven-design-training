package com.trendyol.payment.application;

import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.event.Authorised;
import org.springframework.stereotype.Service;

import java.util.List;

// <<Application Service>>
@Service
public class ChargeHandlerDecider {

    private final List<ChargeHandler<? extends Authorised>> chargeHandlers;

    public ChargeHandlerDecider(List<ChargeHandler<? extends Authorised>> chargeHandlers) {
        this.chargeHandlers = chargeHandlers;
    }

    public ChargeHandler<? extends Authorised> decide(Charge charge) {
        return this.chargeHandlers.stream()
                .filter(c -> c.canExecute(charge))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Charge flow is invalid!"));
    }
}
