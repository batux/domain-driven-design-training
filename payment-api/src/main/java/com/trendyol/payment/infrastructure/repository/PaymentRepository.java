package com.trendyol.payment.infrastructure.repository;

import com.trendyol.payment.domain.model.charge.Payment;
import org.springframework.stereotype.Repository;

// <<Repository>>
@Repository
public class PaymentRepository {

    public Payment save(Payment payment) {
        // save in db
        return payment;
    }

    public Payment update(Payment payment) {
        // update in db
        return payment;
    }

    public boolean delete(Payment payment) {
        // delete in db
        return true;
    }

    public Payment findById(String id) {
        // select from db
        return null;
    }
}
