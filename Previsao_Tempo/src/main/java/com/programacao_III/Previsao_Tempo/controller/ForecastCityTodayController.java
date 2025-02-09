package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateTemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateWindResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherLastTwentyFourHoursResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityTodayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cityforecasttoday")
public class ForecastCityTodayController {


    @Autowired
    private ForecastCityTodayService service;


    @GetMapping("/condition/{nameCity}")
    public ResponseEntity<WeatherConditionsResponseDTO> getConditions(
            @PathVariable String nameCity) {

        return ResponseEntity.ok(service.getCondition(nameCity));
    }


    @GetMapping("/temperatures/{nameCity}")
    public ResponseEntity<ClimateTemperatureResponseDTO> getTemperature(
            @PathVariable String nameCity) {

        return ResponseEntity.ok(service.getTemperature(nameCity));
    }


    @GetMapping("/{nameCity}")
    public ResponseEntity<ClimateResponseDTO> getClimate(
            @PathVariable String nameCity) {
        return ResponseEntity.ok(service.getClimate(nameCity));
    }


    @GetMapping("/wind/{nameCity}")
    public ResponseEntity<ClimateWindResponseDTO> getWindToday(
            @PathVariable String nameCity) {

        return ResponseEntity.ok(service.getWind(nameCity));
    }

    @GetMapping("/lasttwentyfourhours/{nameCity}")
    public ResponseEntity<WeatherLastTwentyFourHoursResponseDTO> getLastTwentyFourHours(
            @PathVariable String nameCity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.ForecastLastTwentyFourHours(nameCity, pageable));
    }
}
