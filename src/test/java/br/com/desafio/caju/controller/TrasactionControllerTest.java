package br.com.desafio.caju.controller;


import br.com.desafio.caju.payload.TransactionRequest;
import br.com.desafio.caju.payload.TransactionResponse;
import br.com.desafio.caju.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrasactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    private TransactionResponse transactionResponse;

    private TransactionRequest transactionRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTransaction();
    }


    @Test
    void whenProcessThenTransaction() {
        when(transactionService.processarTrasaction(any())).thenReturn(transactionResponse);
        ResponseEntity<TransactionResponse> response = transactionController.transaction(transactionRequest);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    private void startTransaction() {

        transactionRequest = new TransactionRequest("123", new BigDecimal("100"), 5811, "PADARIA DO ZE               SAO PAULO BR");


    }

}
