package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.AllForecastFiveDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFiveDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador REST que retorna respostas HTTP.
@RequestMapping("/forecastscityfivedays") // Define o caminho base para os endpoints deste controlador.
public class ForecastCityFiveDaysController {

    @Autowired // Injeta automaticamente a dependência do serviço ForecastCityFiveDaysService.
    private ForecastCityFiveDaysService service;

    // Método para buscar previsões meteorológicas para 5 dias de uma cidade específica com suporte à paginação.
    @GetMapping("/{nameCity}") // Define o endpoint que aceita o nome da cidade como parâmetro na URL.
    public ResponseEntity<ForecastFiveDaysPageAbleResponseDTO> getForecast(
            @PathVariable String nameCity, // Recebe o nome da cidade como parâmetro da URL.
            @RequestParam(defaultValue = "0") int page, // Parâmetro opcional para definir a página atual, com valor padrão 0.
            @RequestParam(defaultValue = "3") int pageSize) { // Parâmetro opcional para definir o tamanho da página, com valor padrão 3.

        // Cria um objeto Pageable para controlar a paginação dos resultados.
        Pageable pageable = PageRequest.of(page, pageSize);
        // Chama o serviço para obter as previsões paginadas e retorna a resposta com status 200 (OK).
        return ResponseEntity.ok(service.getForecastFiveDays(nameCity, pageable));
    }

    // Método para buscar todas as previsões meteorológicas de 5 dias de uma cidade, sem paginação.
    @GetMapping("/allforecasts/{nameCity}") // Define o endpoint que aceita o nome da cidade como parâmetro na URL.
    public ResponseEntity<AllForecastFiveDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity) { // Recebe o nome da cidade como parâmetro da URL.

        // Chama o serviço para obter todas as previsões e retorna a resposta com status 200 (OK).
        return ResponseEntity.ok(service.getAllForecastFiveDays(nameCity));
    }
}
