package com.programacao_III.Previsao_Tempo.infra;

import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.ForecastFifteenDaysException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.exceptions.PagesEndException;
import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CityNotFoundException.class)
    private ResponseEntity<Object> cityNotFoundHandler(CityNotFoundException exception){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(PagesEndException.class)
    private ResponseEntity<Object> pageEnd(PagesEndException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    @ExceptionHandler(ForecastFifteenDaysException.class)
    private ResponseEntity<Object> forecastFifteenDaysError(ForecastFifteenDaysException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


    @ExceptionHandler(InternalServerErrorException.class)
    private ResponseEntity<Object> errorInternal(InternalServerErrorException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
