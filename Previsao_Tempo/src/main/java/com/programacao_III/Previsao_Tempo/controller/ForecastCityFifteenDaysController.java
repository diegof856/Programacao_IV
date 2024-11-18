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
@RequestMapping("/forecatscityfifteendays") // Define o endpoint base para as rotas deste controlador.
public class ForecastCityFifteenDaysController {

    @Autowired // Injeta automaticamente o serviço ForecastCityFifteenDaysService no controlador.
    ForecastCityFifteenDaysService service;

    // Método para buscar previsões de 15 dias para uma cidade específica.
    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFifteenDaysResponseDTO> getForecast(
            @PathVariable String nameCity, // Recebe o nome da cidade como parâmetro na URL.
            @RequestParam(defaultValue = "0") int page, // Parâmetro opcional para a página, valor padrão é 0.
            @RequestParam(defaultValue = "5") int pageSize) { // Parâmetro opcional para tamanho da página, valor padrão é 5.

        Pageable pageable = PageRequest.of(page, pageSize); // Cria um objeto Pageable para paginação.
        return ResponseEntity.ok(service.getForecastFifteenDays(nameCity, pageable));
        // Chama o serviço para obter a previsão e retorna a resposta em formato HTTP.
    }

    // Método para buscar todas as previsões de 15 dias para uma cidade, limitado por uma quantidade específica.
    @GetMapping("/allforecasts/{nameCity}/{quantityForecast}")
    public ResponseEntity<ForecastFifteenDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity, // Recebe o nome da cidade como parâmetro na URL.
            @PathVariable Integer quantityForecast, // Recebe a quantidade máxima de previsões a serem retornadas.
            @RequestParam(defaultValue = "0") int page, // Parâmetro opcional para a página, valor padrão é 0.
            @RequestParam(defaultValue = "5") int pageSize) { // Parâmetro opcional para tamanho da página, valor padrão é 5.

        Pageable pageable = PageRequest.of(page, pageSize); // Cria um objeto Pageable para paginação.
        return ResponseEntity.ok(service.getAllForecastFifteenDays(nameCity, quantityForecast, pageable));
        // Chama o serviço para buscar as previsões e retorna a resposta.
    }

    // Método para buscar previsões de chuva para os próximos 15 dias em uma cidade específica.
    @GetMapping("/forecastsrains/{nameCity}/{quantityForecast}")
    public ResponseEntity<ForecastFifteenDaysRainResponseDTO> getRainsForecastFifteenDays(
            @PathVariable String nameCity, // Recebe o nome da cidade como parâmetro na URL.
            @PathVariable Integer quantityForecast, // Recebe a quantidade máxima de previsões de chuva a serem retornadas.
            @RequestParam(defaultValue = "0") int page, // Parâmetro opcional para a página, valor padrão é 0.
            @RequestParam(defaultValue = "5") int pageSize) { // Parâmetro opcional para tamanho da página, valor padrão é 5.

        Pageable pageable = PageRequest.of(page, pageSize); // Cria um objeto Pageable para paginação.
        return ResponseEntity.ok(service.getListRainsForecastFifteenDays(nameCity, pageable, quantityForecast));
        // Chama o serviço para buscar as previsões de chuva e retorna a resposta.
    }
}
