package com.borlok.transferservice.service;

import com.borlok.transferservice.model.Account;
import com.borlok.transferservice.model.TransferStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
public class TransferOrder {
    private Account sender;
    private Account recipient;
    private BigDecimal value;
    private TransferStatus status = TransferStatus.CREATED;
}
