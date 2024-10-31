package com.programacao_III.Precisao_Tempo.controller;

import com.programacao_III.Precisao_Tempo.dto.ClimateResponseDTO;
import com.programacao_III.Precisao_Tempo.dto.CoordResponseDTO;
import com.programacao_III.Precisao_Tempo.dto.WeatherConditionsResponseDTO;
import com.programacao_III.Precisao_Tempo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private  ClientService service;

    @GetMapping("conditions/{nameCity}")
    public ResponseEntity<WeatherConditionsResponseDTO> getConditions(@PathVariable String nameCity){
        return  ResponseEntity.ok(service.getCondition(nameCity));
    }

    @GetMapping("coordinates/{nameCity}")
    public ResponseEntity<CoordResponseDTO> getCoord(@PathVariable String nameCity){
        return  ResponseEntity.ok(service.getCoord(nameCity));
    }

    @GetMapping("temperature/{nameCity}")
    public  ResponseEntity<ClimateResponseDTO> getTemperature(@PathVariable String nameCity){
        return ResponseEntity.ok(service.getTemperature(nameCity));
    }


}
