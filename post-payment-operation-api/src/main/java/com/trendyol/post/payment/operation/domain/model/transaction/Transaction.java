package com.trendyol.post.payment.operation.domain.model.transaction;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

// <<Entity>>
public class Transaction {

    private String referenceNumber;
    @Getter
    private Boolean success;
    private Date createdDate;
    private ProviderTransactionDetail details;

    public Transaction() {
        this.referenceNumber = UUID.randomUUID().toString();
        this.createdDate = new Date();
    }

    public boolean isForwarded() {
        if(this.details == null) {
            return false;
        }
        return this.details.isForwarded();
    }

    public void setTransactionDetail(ProviderTransactionDetail details) {
        if(details == null) {
            this.success = false;
            return;
        }
        this.success = details.isSuccess();
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
        if(StringUtils.isEmpty(this.referenceNumber) || StringUtils.isEmpty(((Transaction) obj).referenceNumber)) {
            return false;
        }
        return this.referenceNumber.equals(((Transaction) obj).referenceNumber);
    }
}
