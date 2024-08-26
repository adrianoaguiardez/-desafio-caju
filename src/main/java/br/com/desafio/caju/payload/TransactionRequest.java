package br.com.desafio.caju.payload;


import br.com.desafio.caju.model.TransactionAccount;
import br.com.desafio.caju.model.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransactionRequest {


    @NotBlank
    private String account;

    @NotNull()
    @Positive
    private BigDecimal totalAmount;

    @NotNull()
    private int mcc;

    @NotBlank
    private String merchant;

    public TransactionRequest(String account, BigDecimal totalAmount, int mcc, String merchant) {

        this.account = account;
        this.totalAmount = totalAmount;
        this.mcc = mcc;
        this.merchant = merchant;
    }

    public TransactionAccount toModel(Account account){

       return new TransactionAccount(null, account, this.totalAmount, this.mcc, this.merchant);
    }

    public String getAccount() {
        return account;
    }

    public int getMcc() {
        return mcc;
    }

    public String getMerchant() {
        return merchant;
    }
}
