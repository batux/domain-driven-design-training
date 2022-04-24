package com.trendyol.post.payment.operation.domain.model.pos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

// <<Value Object>>
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Secret {

    @Getter
    private String authCode;
    @Getter
    private String username;
    @Getter
    private String password;

    public boolean isValid() {
        return !StringUtils.isEmpty(this.authCode) &&
                !StringUtils.isEmpty(this.username) &&
                !StringUtils.isEmpty(this.password);
    }
}
