package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.AllForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFourDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/forecastscityfourdays")
public class ForecastCityFourDaysController {


    @Autowired
    private ForecastCityFourDaysService service;

    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFourDaysPageableDTO> getForecast(
            @PathVariable String nameCity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);

        return ResponseEntity.ok(service.getForecast(nameCity, pageable));
    }

    @GetMapping("/allforecasts/{nameCity}")
    public ResponseEntity<AllForecastFourDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity) {

        return ResponseEntity.ok(service.getAllForecastFourDays(nameCity));
    }
}
