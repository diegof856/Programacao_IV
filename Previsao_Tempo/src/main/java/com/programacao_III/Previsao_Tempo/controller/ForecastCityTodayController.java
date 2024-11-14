package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateTemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateWindResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.WeatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityTodayService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Anotação @RestController indica que esta classe é um controlador REST
// onde os métodos irão retornar respostas no formato JSON.
@RestController
@RequestMapping("/forecastcitytoday") // A URL base para os métodos deste controlador será "/forecastcitytoday".
public class ForecastCityTodayController {

    // Injeção da dependência do serviço ClientService para acessar a lógica de negócios
    @Autowired
    private ForecastCityTodayService service;

    // Endpoint GET para buscar as condições climáticas de uma cidade
    // Recebe o nome da cidade como parâmetro na URL.
    @GetMapping("condition/{nameCity}")
    public ResponseEntity<WeatherConditionsResponseDTO> getConditions(@PathVariable String nameCity){
        // Chama o serviço para obter as condições climáticas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getCondition(nameCity));
    }

    // Endpoint GET para buscar a temperatura atual de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("temperature/{nameCity}")
    public ResponseEntity<ClimateTemperatureResponseDTO> getTemperature(@PathVariable String nameCity){
        // Chama o serviço para obter a temperatura atual e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getTemperature(nameCity));
    }

    // Endpoint GET para buscar as informações climáticas detalhadas de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("/{nameCity}")
    public ResponseEntity<ClimateResponseDTO> getClimate(@PathVariable String nameCity) {
        // Chama o serviço para obter as informações climáticas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getClimate(nameCity));
    }
    @GetMapping("/wind/{nameCity}")
    public ResponseEntity<ClimateWindResponseDTO>getWindToday(@PathVariable String nameCity){

        return ResponseEntity.ok(service.getWind(nameCity));
    }
}
