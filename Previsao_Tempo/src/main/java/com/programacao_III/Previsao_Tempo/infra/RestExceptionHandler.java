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

// A classe 'RestExceptionHandler' é um 'ControllerAdvice' que trata exceções específicas lançadas em qualquer parte da aplicação.
// Ela permite que o tratamento de exceções seja centralizado, proporcionando respostas apropriadas para os erros que ocorrem.
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // O método 'cityNotFoundHandler' é um manipulador de exceção para 'CityNotFoundException'.
    // Quando uma exceção desse tipo é lançada, a aplicação irá capturá-la e retornar uma resposta com um código HTTP 404 (Not Found),
    // incluindo uma mensagem de erro personalizada.
    @ExceptionHandler(CityNotFoundException.class)
    private ResponseEntity<Object> cityNotFoundHandler(CityNotFoundException exception){
        Map<String, Object> body = new HashMap<>();
        // O corpo da resposta contém duas informações:
        // 1. "Erro" - A mensagem da exceção, obtida pelo método getMessage().
        // 2. "Status" - O código de status HTTP, no caso, 404, indicando que a cidade não foi encontrada.
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        // A resposta é retornada com status 404 e o corpo contendo a mensagem de erro e o status.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // O método 'pageEnd' é um manipulador de exceção para 'PagesEndException'.
    // Quando essa exceção é lançada, a resposta retorna o status HTTP 400 (Bad Request), indicando que não há mais previsões disponíveis.
    @ExceptionHandler(PagesEndException.class)
    private ResponseEntity<Object> pageEnd(PagesEndException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.BAD_REQUEST.value());
        // A resposta é retornada com status 400 e o corpo contendo a mensagem de erro e o status.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // O método 'forecastFifteenDaysError' é um manipulador de exceção para 'ForecastFifteenDaysException'.
    // Quando essa exceção é lançada, a resposta retorna o status HTTP 400, indicando que a quantidade de previsões fornecida
    // está fora dos limites permitidos.
    @ExceptionHandler(ForecastFifteenDaysException.class)
    private ResponseEntity<Object> forecastFifteenDaysError(ForecastFifteenDaysException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.BAD_REQUEST.value());
        // A resposta é retornada com status 400 e o corpo contendo a mensagem de erro e o status.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // O método 'errorInternal' é um manipulador de exceção para 'InternalServerErrorException'.
    // Quando essa exceção é lançada, a resposta retorna o status HTTP 500 (Internal Server Error), indicando que ocorreu
    // um erro interno no servidor durante o processamento da requisição.
    @ExceptionHandler(InternalServerErrorException.class)
    private ResponseEntity<Object> errorInternal(InternalServerErrorException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Erro", exception.getMessage());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        // A resposta é retornada com status 500 e o corpo contendo a mensagem de erro e o status.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
