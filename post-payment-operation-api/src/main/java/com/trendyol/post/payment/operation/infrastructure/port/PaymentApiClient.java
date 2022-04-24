package com.trendyol.post.payment.operation.infrastructure.port;

import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class PaymentApiClient {

    public CompletedPayment fetchById(String id) {
        return new CompletedPayment();
    }
}
