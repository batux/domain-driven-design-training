package com.trendyol.post.payment.operation.controller;

import com.trendyol.post.payment.operation.application.RefundHandler;
import com.trendyol.post.payment.operation.domain.model.RefundRequest;
import com.trendyol.post.payment.operation.domain.model.event.Refunded;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/post/payment/operation")
public class PostPaymentOperationController {

    private final RefundHandler handler;

    public PostPaymentOperationController(RefundHandler handler) {
        this.handler = handler;
    }

    @PostMapping("/refund")
    public ResponseEntity<Refunded> refund(@RequestBody RefundRequest refundRequest) {
        return ResponseEntity.ok(this.handler.runRefundFlow(refundRequest));
    }
}
