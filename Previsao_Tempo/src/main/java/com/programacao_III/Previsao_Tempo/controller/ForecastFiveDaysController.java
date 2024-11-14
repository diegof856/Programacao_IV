package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.ForecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFiveDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cityforecastfivedays")
public class ForecastFiveDaysController {
    @Autowired
    private ForecastCityFiveDaysService service;

    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFiveDaysPageAbleResponseDTO> getForecast(@PathVariable String nameCity,
                                                                           @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize)
    {
        // Cria um objeto Pageable para controlar a paginação dos resultados
        Pageable pageable = PageRequest.of(page, pageSize);
        // Chama o serviço para obter as previsões paginadas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getForecastFiveDays(nameCity, pageable));
    }

}
