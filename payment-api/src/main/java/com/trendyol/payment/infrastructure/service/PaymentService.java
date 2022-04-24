package com.trendyol.payment.infrastructure.service;

import com.trendyol.payment.domain.model.charge.Payment;
import com.trendyol.payment.infrastructure.repository.PaymentRepository;
import org.springframework.stereotype.Service;

// <<Infrastructure Service>>
@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment insert(Payment payment) {
        return this.repository.save(payment);
    }

    public Payment update(Payment payment) {
        return this.repository.update(payment);
    }

    public Payment findById(String id) {
        return this.repository.findById(id);
    }
}
