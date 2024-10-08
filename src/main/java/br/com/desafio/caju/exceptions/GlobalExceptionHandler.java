package br.com.desafio.caju.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AprovacaoException.class)
    public ResponseEntity<ApiError> handleAprovacaoException(AprovacaoException ex) {

        ApiError error = new ApiError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(error);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {

        ApiError error = new ApiError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErroResponse> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> new ErroResponse(x.getField(), x.getDefaultMessage())).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }


}

