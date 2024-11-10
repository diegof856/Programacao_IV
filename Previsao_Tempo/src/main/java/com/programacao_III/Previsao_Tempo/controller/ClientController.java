package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.TemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.ForecastFourDays.ForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.ForecastFourDays.ForecastFourDaysLimitationResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.WeatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Anotação @RestController indica que esta classe é um controlador REST
// onde os métodos irão retornar respostas no formato JSON.
@RestController
@RequestMapping("/cities") // A URL base para os métodos deste controlador será "/cities".
public class ClientController {

    // Injeção da dependência do serviço ClientService para acessar a lógica de negócios
    @Autowired
    private ClientService service;

    // Endpoint GET para buscar as condições climáticas de uma cidade
    // Recebe o nome da cidade como parâmetro na URL.
    @GetMapping("conditions/{nameCity}")
    public ResponseEntity<WeatherConditionsResponseDTO> getConditions(@PathVariable String nameCity){
        // Chama o serviço para obter as condições climáticas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getCondition(nameCity));
    }

    // Endpoint GET para buscar as previsões climáticas paginadas de uma cidade
    // Recebe o nome da cidade na URL e os parâmetros de página (page) e tamanho da página (pageSize)
    @GetMapping("/pagination/{nameCity}/forecasts")
    public ResponseEntity<ForecastFourDaysLimitationResponseDTO> getPaginationForecast(@PathVariable String nameCity,
                                                                                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize)
    {
        // Cria um objeto Pageable para controlar a paginação dos resultados
        Pageable pageable = PageRequest.of(page, pageSize);
        // Chama o serviço para obter as previsões paginadas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getPaginationForecast(nameCity, pageable));
    }

    // Endpoint GET para buscar as previsões climáticas para os próximos 4 dias de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("/{nameCity}/forecasts")
    public ResponseEntity<ForecastFourDaysResponseDTO> getForecast(@PathVariable String nameCity)
    {
        // Chama o serviço para obter as previsões para os próximos 4 dias e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getForecastFourDays(nameCity));
    }

    // Endpoint GET para buscar a temperatura atual de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("temperatures/{nameCity}")
    public ResponseEntity<TemperatureResponseDTO> getTemperature(@PathVariable String nameCity){
        // Chama o serviço para obter a temperatura atual e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getTemperature(nameCity));
    }

    // Endpoint GET para buscar as informações climáticas detalhadas de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("climates/{nameCity}")
    public ResponseEntity<ClimateResponseDTO> getClimate(@PathVariable String nameCity) {
        // Chama o serviço para obter as informações climáticas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getClimate(nameCity));
    }
}
