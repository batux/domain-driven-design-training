package com.trendyol.post.payment.operation.domain.model.refund;

import com.trendyol.post.payment.operation.domain.model.transaction.Transaction;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

// <<Aggregate Root>>
public class Refund {

    @Getter
    private String id;
    @Getter
    private String paymentId;
    private Date createdDate;
    @Getter
    private Money amount;
    private Status status;
    private List<Transaction> transactions;

    public Refund(String paymentId, Money amount) {
        this.id = UUID.randomUUID().toString();
        this.status = Status.INITIALIZED;
        this.createdDate = new Date();
        this.transactions = new LinkedList<>();
        if(!amount.isValid()) {
            throw new RuntimeException("Refund amount is not valid!");
        }
        this.amount = amount;
        this.paymentId = paymentId;
    }

    public boolean isAmountValid() {
        return this.amount.isValid();
    }

    public boolean isSuccessful() {
        return Status.SUCCESS.equals(this.status);
    }

    public boolean isRefunded() {
        return this.isSuccessful() &&
                this.fetchLastSuccessfulTransaction() != null;
    }

    public boolean isForwarded() {
        Transaction transaction = this.fetchLastSuccessfulTransaction();
        return this.isSuccessful() && transaction != null && transaction.isForwarded();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if(transaction.getSuccess()) {
            this.status = Status.SUCCESS;
        } else {
            this.status = Status.FAIL;
        }
    }

    public Transaction fetchLastSuccessfulTransaction() {
        return this.transactions.stream()
                .filter(Transaction::getSuccess)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public Transaction fetchLastUnSuccessfulTransaction() {
        return this.transactions.stream()
                .filter(it -> !it.getSuccess())
                .reduce((first, second) -> second)
                .orElse(null);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(super.equals(obj)) {
            return true;
        }
        if(!this.getClass().equals(obj.getClass())) {
            return false;
        }
        if(StringUtils.isEmpty(this.id) || StringUtils.isEmpty(((Refund) obj).id)) {
            return false;
        }
        return this.id.equals(((Refund) obj).id);
    }
}
