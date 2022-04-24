package com.trendyol.payment.controller;

import com.trendyol.payment.application.ChargeHandler;
import com.trendyol.payment.application.ChargeHandlerDecider;
import com.trendyol.payment.domain.model.charge.Charge;
import com.trendyol.payment.domain.model.charge.event.Authorised;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/charge")
public class ChargeController {

    private final ChargeHandlerDecider handlerDecider;

    public ChargeController(ChargeHandlerDecider handlerDecider) {
        this.handlerDecider = handlerDecider;
    }

    @PostMapping("/authorisation")
    public ResponseEntity<? extends Authorised> authorisation(@RequestBody Charge charge) {
        ChargeHandler<? extends Authorised> chargeHandler = this.handlerDecider.decide(charge);
        return ResponseEntity.ok(chargeHandler.runChargeFlow(charge));
    }
}
