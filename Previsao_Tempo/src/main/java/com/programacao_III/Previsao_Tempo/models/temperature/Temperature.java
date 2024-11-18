package com.programacao_III.Previsao_Tempo.models.temperature;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe Temperature armazena as diferentes medições de temperatura ao longo do dia,
// incluindo as temperaturas mínima, máxima, durante o dia, à noite, ao entardecer e de manhã.
public class Temperature {

    // O campo 'day' representa a temperatura durante o dia, mapeado para o nome "Temperatura durante o dia" no JSON.
    // Também pode ser mapeado a partir do nome "day" no JSON.
    @JsonProperty("Temperatura durante o dia")
    @JsonAlias("day")
    private String day;

    // O campo 'min' representa a temperatura mínima, mapeado para o nome "Temperatura minima" no JSON.
    // Também pode ser mapeado a partir do nome "min" no JSON.
    @JsonProperty("Temperatura minima")
    @JsonAlias("min")
    private String min;

    // O campo 'max' representa a temperatura máxima, mapeado para o nome "Temperatura maxima" no JSON.
    // Também pode ser mapeado a partir do nome "max" no JSON.
    @JsonProperty("Temperatura maxima")
    @JsonAlias("max")
    private String max;

    // O campo 'night' representa a temperatura durante a noite, mapeado para o nome "Temperatura durante a noite" no JSON.
    // Também pode ser mapeado a partir do nome "night" no JSON.
    @JsonProperty("Temperatura durante a noite")
    @JsonAlias("night")
    private String night;

    // O campo 'eve' representa a temperatura registrada ao entardecer, mapeado para o nome "Temperatura registrada no entardecer" no JSON.
    // Também pode ser mapeado a partir do nome "eve" no JSON.
    @JsonProperty("Temperatura registrada no entardecer")
    @JsonAlias("eve")
    private String eve;

    // O campo 'morn' representa a temperatura registrada durante a manhã, mapeado para o nome "Temperatura registrada durante a manhã" no JSON.
    // Também pode ser mapeado a partir do nome "morn" no JSON.
    @JsonProperty("Temperatura registrada durante a manhã")
    @JsonAlias("morn")
    private String morn;

    // Construtor padrão (sem parâmetros), usado para criar um objeto Temperature sem valores iniciais.
    public Temperature(){}

    // Construtor parametrizado para inicializar os campos com valores fornecidos.
    public Temperature(String day, String morn, String eve, String max, String min, String night) {
        this.day = day;    // Inicializa a temperatura durante o dia.
        this.morn = morn;  // Inicializa a temperatura de manhã.
        this.eve = eve;    // Inicializa a temperatura ao entardecer.
        this.max = max;    // Inicializa a temperatura máxima.
        this.min = min;    // Inicializa a temperatura mínima.
        this.night = night; // Inicializa a temperatura durante a noite.
    }

    // Método getter para a temperatura durante o dia.
    // Retorna a temperatura com a unidade "ºC" (graus Celsius).
    public String getDay() {
        return day+"ºC";  // Retorna a temperatura durante o dia com a unidade "ºC".
    }

    // Método getter para a temperatura mínima.
    // Retorna a temperatura mínima com a unidade "ºC" (graus Celsius).
    public String getMin() {
        return min+"ºC";   // Retorna a temperatura mínima com a unidade "ºC".
    }

    // Método getter para a temperatura durante a noite.
    // Retorna a temperatura noturna com a unidade "ºC" (graus Celsius).
    public String getNight() {
        return night+"ºC";  // Retorna a temperatura durante a noite com a unidade "ºC".
    }

    // Método getter para a temperatura máxima.
    // Retorna a temperatura máxima com a unidade "ºC" (graus Celsius).
    public String getMax() {
        return max+"ºC";   // Retorna a temperatura máxima com a unidade "ºC".
    }

    // Método getter para a temperatura ao entardecer.
    // Retorna a temperatura ao entardecer com a unidade "ºC" (graus Celsius).
    public String getEve() {
        return eve+"ºC";    // Retorna a temperatura ao entardecer com a unidade "ºC".
    }

    // Método getter para a temperatura durante a manhã.
    // Retorna a temperatura matutina com a unidade "ºC" (graus Celsius).
    public String getMorn() {
        return morn+"ºC";   // Retorna a temperatura durante a manhã com a unidade "ºC".
    }
}
