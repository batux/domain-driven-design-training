package com.trendyol.payment.domain.model.charge.transaction;

import com.trendyol.pos.management.domain.model.PosDetail;
import com.trendyol.pos.management.domain.model.ProviderType;
import com.trendyol.pos.management.domain.model.Type;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

// <<Entity>>
public class Transaction {

    private String referenceNumber;
    private boolean success;
    private PosDetail posDetails;
    private Date createdDate;
    private ProviderTransactionDetail details;

    public Transaction(PosDetail posDetails) {
        this.referenceNumber = UUID.randomUUID().toString();
        this.createdDate = new Date();
        this.posDetails = posDetails;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isAsync() {
        if(this.posDetails == null) {
            return false;
        }
        return Type.ASYNC.equals(this.posDetails.getType());
    }

    public ProviderType currentProviderType() {
        if(this.posDetails == null) {
            return ProviderType.UNKNOWN;
        }
        return this.posDetails.getProviderType();
    }

    public String accessProviderAction() {
        if(this.details == null) {
            return "";
        }
        return this.details.accessProviderAction();
    }

    public String getPosId() {
        if(this.posDetails == null) {
            return "";
        }
        return this.posDetails.getId();
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
