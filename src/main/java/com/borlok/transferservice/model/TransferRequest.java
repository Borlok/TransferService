package com.borlok.transferservice.model;

import com.borlok.transferservice.exception.user.AccountException;
import com.borlok.transferservice.exception.user.AccountExceptionMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransferRequest {
    private Long userId;
    private BigDecimal value;

    public TransferRequest(Long userId, BigDecimal value) {
        this.userId = userId;
        this.value = value;
        validateValue(value);
    }

    private void validateValue(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0)
            throw new AccountException(AccountExceptionMessage.VALUE_CAN_NOT_BE_NEGATIVE);
        if (value.scale() > 2)
            throw new AccountException(AccountExceptionMessage.INVALID_TRANSFER_AMOUNT_PRECISION);
    }
}
