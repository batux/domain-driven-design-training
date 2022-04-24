package com.trendyol.post.payment.operation.domain.service;

import com.trendyol.post.payment.operation.domain.model.RefundRequest;
import com.trendyol.post.payment.operation.domain.model.payment.CompletedPayment;
import com.trendyol.post.payment.operation.domain.model.refund.Money;
import org.springframework.stereotype.Component;

@Component
public class RefundableOperationChecker {

    public void check(CompletedPayment payment, RefundRequest refundRequest) {
        if(payment == null || refundRequest == null) {
            throw new RuntimeException("Invalid refund operation!");
        }
        if(!payment.isSuccessful()) {
            throw new RuntimeException("Payment is not paid, you can not make refund!");
        }
        if(payment.isForwarded() && payment.isSuccessful()) {
            throw new RuntimeException("You have forwarded refund request, you have to wait until current request is completed!");
        }

        Money paidAmount = payment.getAmount();
        Money refundAmount = refundRequest.getRefundAmount();
        if(paidAmount == null || refundAmount == null) {
            throw new RuntimeException("Amount values can not be empty or null!");
        }
        if(refundAmount.isGreaterThan(paidAmount)) {
            throw new RuntimeException("Refund amount can not be greater than paid amount!");
        }
    }
}
