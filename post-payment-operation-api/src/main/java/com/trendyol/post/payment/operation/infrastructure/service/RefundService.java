package com.trendyol.post.payment.operation.infrastructure.service;

import com.trendyol.post.payment.operation.domain.model.refund.Refund;
import com.trendyol.post.payment.operation.infrastructure.repository.RefundRepository;
import org.springframework.stereotype.Service;

// <<Infrastructure Service>>
@Service
public class RefundService {

    private final RefundRepository repository;

    public RefundService(RefundRepository repository) {
        this.repository = repository;
    }

    public Refund insert(Refund refund) {
        return this.repository.save(refund);
    }

    public Refund update(Refund refund) {
        return this.repository.update(refund);
    }

    public boolean delete(Refund refund) {
        return this.repository.delete(refund);
    }

    public Refund findById(String id) {
        return this.repository.findById(id);
    }
}
