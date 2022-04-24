package com.trendyol.post.payment.operation.infrastructure.repository;

import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import org.springframework.stereotype.Repository;

@Repository
public class RefundRepository {

    public Refund save(Refund refund) {
        // save in db
        return refund;
    }

    public Refund update(Refund refund) {
        // update in db
        return refund;
    }

    public boolean delete(Refund refund) {
        // delete in db
        return true;
    }

    public Refund findById(String id) {
        // select from db
        return null;
    }
}
