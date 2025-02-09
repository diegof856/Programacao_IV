package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays.ForecastFifteenDaysRainResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays.ForecastFifteenDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFifteenDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/forecatscityfifteendays")
public class ForecastCityFifteenDaysController {

    @Autowired
    ForecastCityFifteenDaysService service;


    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFifteenDaysResponseDTO> getForecast(
            @PathVariable String nameCity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.getForecastFifteenDays(nameCity, pageable));

    }


    @GetMapping("/allforecasts/{nameCity}/{quantityForecast}")
    public ResponseEntity<ForecastFifteenDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity,
            @PathVariable Integer quantityForecast,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.getAllForecastFifteenDays(nameCity, quantityForecast, pageable));

    }


    @GetMapping("/forecastsrains/{nameCity}/{quantityForecast}")
    public ResponseEntity<ForecastFifteenDaysRainResponseDTO> getRainsForecastFifteenDays(
            @PathVariable String nameCity,
            @PathVariable Integer quantityForecast,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.getListRainsForecastFifteenDays(nameCity, pageable, quantityForecast));

    }
}
