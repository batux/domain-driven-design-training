package com.trendyol.post.payment.operation.infrastructure.port;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import org.springframework.stereotype.Component;

// <<Infrastructure Service>>
@Component
public class PosApiClient {

    public Pos fetchById(String id) {
        return new Pos();
    }
}
