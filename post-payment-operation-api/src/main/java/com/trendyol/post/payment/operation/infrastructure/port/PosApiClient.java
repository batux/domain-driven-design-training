package com.trendyol.post.payment.operation.infrastructure.port;

import com.trendyol.post.payment.operation.domain.model.pos.Pos;
import org.springframework.stereotype.Component;

@Component
public class PosApiClient {

    public Pos fetchById(String id) {
        return new Pos();
    }
}
