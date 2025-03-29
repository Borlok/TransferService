package com.borlok.transferservice.service.util;


import com.borlok.transferservice.service.TransferOrder;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */


public interface TransferManager {
    TransferOrder createTransferOrder(Long from, Long to, BigDecimal value);
    void perform(TransferOrder transferOrder);
}
