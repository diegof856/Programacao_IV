package com.programacao_III.Previsao_Tempo.controller;

import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.service.ForecastCityFourDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cityforecastfourdays")
public class ForecastFourDaysController {
    // Injeção da dependência do serviço ClientService para acessar a lógica de negócios
    @Autowired
    private ForecastCityFourDaysService service;

    // Endpoint GET para buscar as previsões climáticas paginadas de uma cidade
    // Recebe o nome da cidade na URL e os parâmetros de página (page) e tamanho da página (pageSize)
    @GetMapping("/{nameCity}")
    public ResponseEntity<ForecastFourDaysPageableDTO> getForecast(@PathVariable String nameCity,
                                                                   @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize)
    {
        // Cria um objeto Pageable para controlar a paginação dos resultados
        Pageable pageable = PageRequest.of(page, pageSize);
        // Chama o serviço para obter as previsões paginadas e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getForecast(nameCity, pageable));
    }

    // Endpoint GET para buscar as previsões climáticas para os próximos 4 dias de uma cidade
    // Recebe o nome da cidade na URL.
    @GetMapping("/allforecasts/{nameCity}")
    public ResponseEntity<ForecastFourDaysResponseDTO> getAllForecast(@PathVariable String nameCity)
    {
        // Chama o serviço para obter as previsões para os próximos 4 dias e retorna a resposta com status 200 (OK)
        return ResponseEntity.ok(service.getAllForecastFourDays(nameCity));
    }
}
