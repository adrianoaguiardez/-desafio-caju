package br.com.desafio.caju.exceptions;

public class ApiError {
    private String code;
    public ApiError(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
