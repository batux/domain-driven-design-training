package com.trendyol.payment.domain.model.charge;

import com.trendyol.payment.domain.model.Customer;
import com.trendyol.payment.domain.model.Money;
import com.trendyol.payment.domain.model.charge.transaction.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.*;

// <<Aggregate Root>>
@NoArgsConstructor
public class Payment {

    @Getter
    private String id;
    private Type type;
    private int installmentCount;
    private Date createdDate;
    private Customer user;
    private Money amount;
    @Getter
    private Status status;
    private CreditCard card;
    private List<Transaction> transactions;

    public Payment(Type type, int installmentCount, Customer user, Money amount, CreditCard card) {
        this.initialize(type, installmentCount, user, amount);
        if(this.card != null && !this.card.isValid()) {
            throw new RuntimeException("Credit Card is not valid!");
        }
        this.card = card;
    }

    public Payment(Type type, int installmentCount, Customer user, Money amount) {
        this.initialize(type, installmentCount, user, amount);
    }

    private void initialize(Type type, int installmentCount, Customer user, Money amount) {
        this.createdDate = new Date();
        this.status = Status.INITIALIZED;
        this.transactions = new LinkedList<>();
        this.id = UUID.randomUUID().toString();
        this.type = type;
        if(installmentCount < 1) {
            throw new RuntimeException("Installment value is not valid!");
        }
        this.installmentCount = installmentCount;
        this.user = user;
        if(!amount.isValid()) {
            throw new RuntimeException("Amount is not valid!");
        }
        this.amount = amount;
    }

    public boolean isValidAmount() {
        return this.amount != null && this.isValidAmount();
    }

    public boolean isSuccessful() {
        return Status.SUCCESS.equals(this.status);
    }

    public boolean isAsync() {
        return Type.PAY_ASYNC.equals(this.type);
    }

    public boolean isCreditCardUtilized() {
        return this.card != null && this.card.isValid();
    }

    public boolean isGreaterThan(Money amount) {
        return this.amount.isGreaterThan(amount);
    }

    public boolean isLowerThan(Money amount) {
        return this.amount.isLowerThan(amount);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if(transaction.isSuccess()) {
            this.status = Status.SUCCESS;
        } else {
            this.status = Status.FAIL;
        }
    }

    public String fetchLastSuccessfulTransactionPosId() {
        Transaction transaction = this.fetchLastSuccessfulTransaction();
        if(transaction == null) {
            return "";
        }
        return transaction.getPosId();
    }

    public String accessProviderAction() {
        Transaction transaction = this.fetchLastSuccessfulTransaction();
        if(transaction == null) {
            return "";
        }
        return transaction.accessProviderAction();
    }

    public Transaction fetchLastSuccessfulTransaction() {
        return this.transactions.stream()
                .filter(Transaction::isSuccess)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public Transaction fetchLastUnSuccessfulTransaction() {
        return this.transactions.stream()
                .filter(tr -> !tr.isSuccess())
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
        if(StringUtils.isEmpty(this.id) || StringUtils.isEmpty(((Payment) obj).id)) {
            return false;
        }
        return this.id.equals(((Payment) obj).id);
    }
}
