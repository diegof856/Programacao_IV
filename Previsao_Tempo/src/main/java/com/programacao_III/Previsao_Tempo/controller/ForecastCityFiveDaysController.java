package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.AllForecastFiveDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFiveDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/forecastscityfivedays")
public class ForecastCityFiveDaysController {

    @Autowired
    private ForecastCityFiveDaysService service;


    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFiveDaysPageAbleResponseDTO> getForecast(
            @PathVariable String nameCity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pageSize) {


        Pageable pageable = PageRequest.of(page, pageSize);

        return ResponseEntity.ok(service.getForecastFiveDays(nameCity, pageable));
    }

    @GetMapping("/allforecasts/{nameCity}")
    public ResponseEntity<AllForecastFiveDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity) {


        return ResponseEntity.ok(service.getAllForecastFiveDays(nameCity));
    }
}
