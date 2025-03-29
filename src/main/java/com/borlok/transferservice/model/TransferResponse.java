package com.borlok.transferservice.model;

import com.borlok.transferservice.service.TransferOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@AllArgsConstructor
public class TransferResponse {
    private Long transferFrom;
    private Long transferTo;
    private BigDecimal value;
    private TransferStatus status;

    public static TransferResponse of(TransferOrder transferOrder) {
        return new TransferResponse(transferOrder.getSender().getId(), transferOrder.getRecipient().getId(), transferOrder.getValue(), transferOrder.getStatus());
    }
}
