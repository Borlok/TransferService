package com.borlok.transferservice.rest.it;

import com.borlok.transferservice.Utils;
import com.borlok.transferservice.exception.user.AccountException;
import com.borlok.transferservice.model.*;
import com.borlok.transferservice.repository.AccountRepository;
import com.borlok.transferservice.repository.EmailRepository;
import com.borlok.transferservice.repository.PhoneRepository;
import com.borlok.transferservice.repository.UserRepository;
import com.borlok.transferservice.rest.TransferRestControllerV1;
import com.borlok.transferservice.service.TokenService;
import com.borlok.transferservice.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class TransferRestControllerV1Test extends TestcontainersStart {
    private MockMvc client;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    User savedUser1;
    User savedUser2;

    @BeforeAll
    void beforeAll() {
        client = MockMvcBuilders
                .standaloneSetup(new TransferRestControllerV1(transferService, tokenService))
                .build();
    }


    @BeforeEach
    public void setup() {
        accountRepository.deleteAll();
        userRepository.deleteAll();
        emailRepository.deleteAll();
        phoneRepository.deleteAll();

        User user1 = Utils.TestUser("Petr");
        User user2 = Utils.TestUser("Grisha");

        Email email1 = Utils.TestEmail(user1);
        Email email2 = Utils.TestEmail(user2);

        Phone phone1 = Utils.TestPhone(user1, "71111111111");
        Phone phone2 = Utils.TestPhone(user2, "72222222222");

        user1.setEmails(List.of(email1));
        user1.setPhones(List.of(phone1));

        user2.setEmails(List.of(email2));
        user2.setPhones(List.of(phone2));

        savedUser1 = userRepository.save(user1);
        savedUser2 = userRepository.save(user2);

        Account account1 = Utils.TestAccount(savedUser1);
        Account account2 = Utils.TestAccount(savedUser2);

        accountRepository.save(account1);
        accountRepository.save(account2);
    }

    @Test
    void givenTwoUsersWIth1000Balances_whenOneOfUsersTransfer500Money_thenFirstHave500AndSecondHave1500() throws Exception {
        assertTrue(userRepository.findById(savedUser1.getId()).isPresent());
        assertTrue(emailRepository.findByEmail("Petr@test.ts").isPresent());

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setUserId(savedUser2.getId());
        transferRequest.setValue(new BigDecimal("500.00"));

        client.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", getToken())
                        .content(new ObjectMapper().writeValueAsString(transferRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transferFrom", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.transferTo", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is("SUCCESS")));

        assertEquals(new BigDecimal("500.00"), accountRepository.findByUserId(savedUser1.getId()).get().getBalance());
        assertEquals(new BigDecimal("1500.00"), accountRepository.findByUserId(savedUser2.getId()).get().getBalance());
    }

    @Test
    void givenTwoUsersWIth1000Balances_whenOneOfUsersTransfer1500Money_thenWeHaveFailedTransferWithExceptionNotEnoughMoney() throws Exception{
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setUserId(savedUser2.getId());
        transferRequest.setValue(new BigDecimal("1500.00"));

        client.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", getToken())
                        .content(new ObjectMapper().writeValueAsString(transferRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("NOT_ENOUGH_MONEY")));
    }

    @Test
    void givenCreatingTransferRequest_whenWeCreateItWithNegative500Money_thenWeHaveAnException() throws Exception{
        assertThrows(AccountException.class, () -> {
            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setUserId(savedUser2.getId());
            transferRequest.setValue(new BigDecimal("-500.00"));
        });
        assertThrows(AccountException.class, () -> {
            new TransferRequest(savedUser2.getId(), new BigDecimal("-500"));
        });
    }

    void givenTwoUsersWIth1000Balances_whenOneOfUsersTransferNegative500Money_thenWeHaveFailedTransferWithExceptionNotEnoughMoney() throws Exception {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setUserId(savedUser2.getId());
        transferRequest.setValue(new BigDecimal("-500.00"));

        client.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", getToken())
                        .content(new ObjectMapper().writeValueAsString(transferRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("NOT_ENOUGH_MONEY")));
    }
}
