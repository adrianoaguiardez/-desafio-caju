package br.com.desafio.caju.payload;

public class TransactionResponse {

    private String code;
    public TransactionResponse(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
