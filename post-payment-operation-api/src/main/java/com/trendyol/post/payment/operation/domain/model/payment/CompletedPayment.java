package com.trendyol.post.payment.operation.domain.model.payment;

import com.trendyol.post.payment.operation.domain.model.pos.Type;
import com.trendyol.post.payment.operation.domain.model.refund.Money;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CompletedPayment {

    @Getter
    private String id;
    @Getter
    private String posId;
    @Getter
    private Money amount;
    private Type type;
    private Status status;

    public boolean isSuccessful() {
        return Status.SUCCESS.equals(this.status);
    }

    public boolean isForwarded() {
        return Type.ASYNC.equals(this.type);
    }
}
