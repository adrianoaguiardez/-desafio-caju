package br.com.desafio.caju.services;


import br.com.desafio.caju.exceptions.AprovacaoException;
import br.com.desafio.caju.model.Account;
import br.com.desafio.caju.model.Balance;
import br.com.desafio.caju.model.ClassificacaoEnum;
import br.com.desafio.caju.model.TransactionAccount;
import br.com.desafio.caju.payload.TransactionRequest;
import br.com.desafio.caju.payload.TransactionResponse;
import br.com.desafio.caju.repository.BalanceRepository;
import br.com.desafio.caju.repository.TransactionRepository;
import br.com.desafio.caju.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;


public class TransactionServerTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BalanceRepository balanceRepository;


    private TransactionAccount transaction;

    private Balance balance;

    private Balance balanceCash;

    private Account account;

    private TransactionRequest transactionRequest;

    private TransactionRequest transactionRequestErro;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTransacion();
    }

    @Test
    void whenProcessThenreturnSucess() {
        when(balanceRepository.findByIdBalanceAndAccount("123", ClassificacaoEnum.MEAL)).thenReturn(balance);


        TransactionResponse response = service.processarTrasaction(transactionRequest);
        when(transactionRepository.save(any())).thenReturn(transaction);
        transactionRepository.save(transaction);
        assertNotNull(response);

        assertEquals(TransactionResponse.class, response.getClass());
        assertEquals("00", response.getCode());
        assertEquals(new BigDecimal("100"), balance.getAmount());
    }

    @Test
    void whenProcessThenreturnError() {
        when(balanceRepository.findByIdBalanceAndAccount("123", ClassificacaoEnum.MEAL)).thenReturn(balance);
        when(balanceRepository.findByIdBalanceAndAccount("123", ClassificacaoEnum.CASH)).thenReturn(balanceCash);


        try {
            TransactionResponse response = service.processarTrasaction(transactionRequestErro);
            when(transactionRepository.save(any())).thenReturn(transaction);
            transactionRepository.save(transaction);
            assertNotNull(response);

            assertEquals(TransactionResponse.class, response.getClass());
            assertEquals("00", response.getCode());

        } catch (AprovacaoException ex) {

            assertEquals(AprovacaoException.class, ex.getClass());
            assertEquals("51", ex.getMessage());


        }
    }


    private void startTransacion() {

        account = new Account(1L, "123");
        account.adicionarBalance(new BigDecimal("200"));
        transactionRequest = new TransactionRequest("123", new BigDecimal("100"), 5811, "PADARIA DO ZE               SAO PAULO BR");
        transactionRequestErro = new TransactionRequest("123", new BigDecimal("300"), 5811, "PADARIA DO ZE               SAO PAULO BR");
        balance = new Balance(1L, ClassificacaoEnum.MEAL, new BigDecimal("200"), account);
        balanceCash = new Balance(1L, ClassificacaoEnum.CASH, new BigDecimal("200"), account);
        transaction = transactionRequest.toModel(account);

    }

}
