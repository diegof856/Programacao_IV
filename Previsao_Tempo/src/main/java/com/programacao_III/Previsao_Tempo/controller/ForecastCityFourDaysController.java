package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.AllForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFourDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Define que esta classe é um controlador REST, retornando dados diretamente em formato JSON.
@RequestMapping("/v1/forecastscityfourdays") // Configura o caminho base para os endpoints deste controlador.
public class ForecastCityFourDaysController {

    // Injeção de dependência para acessar a lógica de negócios do serviço ForecastCityFourDaysService.
    @Autowired
    private ForecastCityFourDaysService service;

    // Endpoint para buscar previsões climáticas paginadas de uma cidade específica.
    @GetMapping("/{nameCity}") // Define que este método será acionado para requisições GET no endpoint "/{nameCity}".
    public ResponseEntity<ForecastFourDaysPageableDTO> getForecast(
            @PathVariable String nameCity, // Recebe o nome da cidade como parâmetro da URL.
            @RequestParam(defaultValue = "0") int page, // Parâmetro opcional para a página atual, com valor padrão 0.
            @RequestParam(defaultValue = "5") int pageSize) { // Parâmetro opcional para o tamanho da página, com valor padrão 5.

        // Cria um objeto Pageable para gerenciar paginação (número da página e quantidade de itens por página).
        Pageable pageable = PageRequest.of(page, pageSize);

        // Chama o serviço para obter as previsões paginadas para a cidade e retorna como resposta com status HTTP 200 (OK).
        return ResponseEntity.ok(service.getForecast(nameCity, pageable));
    }

    // Endpoint para buscar todas as previsões climáticas dos próximos 4 dias de uma cidade específica.
    @GetMapping("/allforecasts/{nameCity}") // Define que este método será acionado para requisições GET no endpoint "/allforecasts/{nameCity}".
    public ResponseEntity<AllForecastFourDaysResponseDTO> getAllForecast(
            @PathVariable String nameCity) { // Recebe o nome da cidade como parâmetro da URL.

        // Chama o serviço para buscar todas as previsões climáticas dos próximos 4 dias e retorna com status HTTP 200 (OK).
        return ResponseEntity.ok(service.getAllForecastFourDays(nameCity));
    }
}
