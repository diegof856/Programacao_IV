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

// Declaração de que esta classe é um controlador REST, que irá manipular requisições HTTP e retornar respostas no formato JSON.
@RestController
@RequestMapping("/cityforecasttoday") // Define a URL base para todos os endpoints deste controlador.
public class ForecastCityTodayController {

    // Injeção de dependência do serviço ForecastCityTodayService para acessar a lógica de negócios.
    @Autowired
    private ForecastCityTodayService service;

    // Endpoint GET para obter as condições climáticas atuais de uma cidade específica.
    @GetMapping("/condition/{nameCity}") // Define o caminho "/condition/{nameCity}" para este método.
    public ResponseEntity<WeatherConditionsResponseDTO> getConditions(
            @PathVariable String nameCity) { // Extrai o nome da cidade da URL.
        // Chama o serviço para obter as condições climáticas da cidade e retorna o resultado com status 200 (OK).
        return ResponseEntity.ok(service.getCondition(nameCity));
    }

    // Endpoint GET para obter a temperatura atual de uma cidade específica.
    @GetMapping("/temperatures/{nameCity}") // Define o caminho "/temperatures/{nameCity}" para este método.
    public ResponseEntity<ClimateTemperatureResponseDTO> getTemperature(
            @PathVariable String nameCity) { // Extrai o nome da cidade da URL.
        // Chama o serviço para obter a temperatura da cidade e retorna o resultado com status 200 (OK).
        return ResponseEntity.ok(service.getTemperature(nameCity));
    }

    // Endpoint GET para obter informações climáticas detalhadas de uma cidade.
    @GetMapping("/{nameCity}") // Define o caminho "/{nameCity}" para este método.
    public ResponseEntity<ClimateResponseDTO> getClimate(
            @PathVariable String nameCity) { // Extrai o nome da cidade da URL.
        // Chama o serviço para obter as informações climáticas e retorna o resultado com status 200 (OK).
        return ResponseEntity.ok(service.getClimate(nameCity));
    }

    // Endpoint GET para obter informações sobre o vento atual de uma cidade específica.
    @GetMapping("/wind/{nameCity}") // Define o caminho "/wind/{nameCity}" para este método.
    public ResponseEntity<ClimateWindResponseDTO> getWindToday(
            @PathVariable String nameCity) { // Extrai o nome da cidade da URL.
        // Chama o serviço para obter as informações do vento e retorna o resultado com status 200 (OK).
        return ResponseEntity.ok(service.getWind(nameCity));
    }

    // Endpoint GET para obter as condições climáticas das últimas 24 horas de uma cidade específica, com suporte a paginação.
    @GetMapping("/lasttwentyfourhours/{nameCity}") // Define o caminho "/lasttwentyfourhours/{nameCity}" para este método.
    public ResponseEntity<WeatherLastTwentyFourHoursResponseDTO> getLastTwentyFourHours(
            @PathVariable String nameCity, // Extrai o nome da cidade da URL.
            @RequestParam(defaultValue = "0") int page, // Define o número da página (valor padrão: 0).
            @RequestParam(defaultValue = "5") int pageSize) { // Define o tamanho da página (valor padrão: 5).

        // Cria um objeto Pageable para controlar a paginação dos resultados.
        Pageable pageable = PageRequest.of(page, pageSize);

        // Chama o serviço para obter as condições climáticas das últimas 24 horas e retorna o resultado com status 200 (OK).
        return ResponseEntity.ok(service.ForecastLastTwentyFourHours(nameCity, pageable));
    }
}
