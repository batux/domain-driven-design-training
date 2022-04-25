package com.trendyol.payment.domain.model.pos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Url {
    @Getter
    private String chargeUrl;
    @Getter
    private String refundUrl;
    @Getter
    private String queryUrl;

    public boolean isValid() {
        return StringUtils.isEmpty(this.chargeUrl) &&
                StringUtils.isEmpty(this.refundUrl) &&
                StringUtils.isEmpty(this.queryUrl);
    }
}
