package br.com.desafio.caju.controller;

import br.com.desafio.caju.payload.TransactionRequest;
import br.com.desafio.caju.payload.TransactionResponse;
import br.com.desafio.caju.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping(path = "transactions")
public class TransactionController {

    public final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public  ResponseEntity<TransactionResponse> transaction(@RequestBody @Valid TransactionRequest transactionRequest){
        TransactionResponse transactionResponse  =  transactionService.processarTrasaction(transactionRequest);


        return ResponseEntity.ok().body(transactionResponse);
    }
}
