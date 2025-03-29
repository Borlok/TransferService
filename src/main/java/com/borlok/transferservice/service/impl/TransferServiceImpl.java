package com.borlok.transferservice.service.impl;

import com.borlok.transferservice.model.TransferStatus;
import com.borlok.transferservice.service.TransferOrder;
import com.borlok.transferservice.service.TransferService;
import com.borlok.transferservice.service.util.TransferManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */

@Data
@Slf4j
@Service
public class TransferServiceImpl implements TransferService {
    private final TransferManager transferManager;

    @Override
    @Transactional
    public TransferOrder transfer(Long senderId, Long recipientId, BigDecimal value) {
        log.info("transfer from {} to {} : {}", senderId, recipientId, value);
        TransferOrder transferOrder = transferManager.createTransferOrder(senderId, recipientId, value);
        try {
            transferManager.perform(transferOrder);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transferOrder.setStatus(TransferStatus.FAILED);
            return transferOrder;
        }
        transferOrder.setStatus(TransferStatus.SUCCESS);
        return transferOrder;
    }
}
