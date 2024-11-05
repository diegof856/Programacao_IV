package com.programacao_III.Previsao_Tempo.dto.ClimateDTO;

public record ClimateResponseDTO(String Temperatura
        ,String Umidade_Relativa_Do_Ar
        ,String Sensacao_Termica
        ,String Pressão_Atmosferica
        ,String Temperatura_Minima
        ,String Temperatura_Maxima
        ,String Pressão_Atmosferica_Nivel_Do_Mar
        ,String Pressão_Atmosferica_Nivel_Da_Terra
        ,String Cidade) {
}
