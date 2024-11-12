package com.programacao_III.Previsao_Tempo.infra;

import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CityNotFoundException.class)
    private ResponseEntity<Object> cityNotFoundHandler(CityNotFoundException exception){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro:",exception.getMessage());
        body.put("status",HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
