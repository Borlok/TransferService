package com.borlok.transferservice.service.util.impl;

import com.borlok.transferservice.exception.user.AccountException;
import com.borlok.transferservice.exception.user.AccountExceptionMessage;
import com.borlok.transferservice.model.Account;
import com.borlok.transferservice.service.AccountService;
import com.borlok.transferservice.service.TransferOrder;
import com.borlok.transferservice.service.util.TransferManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Erofeevskiy Yuriy
 */
@Data
@Slf4j
@Component
public class TransferManagerImpl implements TransferManager {
    private final AccountService accountService;

    @Override
    public TransferOrder createTransferOrder(Long from, Long to, BigDecimal value) {
        TransferOrder transferOrder = new TransferOrder();
        Account senderAccount = accountService.getByUserId(from);
        if (senderAccount.getBalance().compareTo(value) < 0)
            throw new AccountException(AccountExceptionMessage.NOT_ENOUGH_MONEY);
        transferOrder.setSender(senderAccount);
        transferOrder.setRecipient(accountService.getByUserId(to));
        transferOrder.setValue(value);
        return transferOrder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public void perform(TransferOrder transferOrder) {
        log.info("Performing transfer order {}", transferOrder);
        accountService.extractFrom(transferOrder.getSender(), transferOrder.getValue());
        accountService.addTo(transferOrder.getRecipient(), transferOrder.getValue());
    }
}
