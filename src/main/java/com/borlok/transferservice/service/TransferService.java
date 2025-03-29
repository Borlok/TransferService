package com.borlok.transferservice.service;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */


public interface TransferService {
    TransferOrder transfer(Long senderId, Long recipientId, BigDecimal value);
}
