package com.programacao_III.Previsao_Tempo.models.feelslike;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe FeelsLike representa as informações sobre a sensação térmica em diferentes períodos do dia,
// como durante o dia, noite, entardecer e manhã. Essas informações são frequentemente retornadas em APIs
// de previsão do tempo para indicar como a temperatura real é percebida pelo corpo humano em diferentes momentos.
public class FeelsLike {

    // O campo 'day' representa a sensação térmica durante o dia.
    // A anotação @JsonProperty é usada para mapear o campo JSON para o nome do atributo 'day' em Java.
    // A anotação @JsonAlias permite que o campo no JSON seja mapeado com o nome 'day' ou 'Sensação Termica durante o dia'.
    @JsonProperty("Sensacao_Termica_Durante_O_Dia")
    @JsonAlias("day")
    private String day;

    // O campo 'night' representa a sensação térmica durante a noite.
    // A anotação @JsonProperty mapeia o campo JSON para o nome do atributo 'night' em Java.
    // A anotação @JsonAlias permite que o campo seja lido com o nome 'night' ou 'Sensação Termica durante a noite'.
    @JsonProperty("Sensacao_Termica_Durante_A_Noite")
    @JsonAlias("night")
    private String night;

    // O campo 'eve' representa a sensação térmica durante o entardecer.
    // A anotação @JsonProperty mapeia o campo JSON para o nome do atributo 'eve' em Java.
    // A anotação @JsonAlias permite que o campo seja lido com o nome 'eve' ou 'Sensação Termica durante o entardecer'.
    @JsonProperty("Sensacao_Termica_Durante_O_Entardecer")
    @JsonAlias("eve")
    private String eve;

    // O campo 'morn' representa a sensação térmica durante a manhã.
    // A anotação @JsonProperty mapeia o campo JSON para o nome do atributo 'morn' em Java.
    // A anotação @JsonAlias permite que o campo seja lido com o nome 'morn' ou 'Sensação Termica durante a manhã'.
    @JsonProperty("Sensacao_Termica_Durante_A_Manhã")
    @JsonAlias("morn")
    private String morn;

    // Construtor padrão necessário para a deserialização do JSON.
    public FeelsLike(){}

    // Construtor parametrizado para inicializar os campos com valores fornecidos.
    public FeelsLike(String day, String night, String eve, String morn) {
        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    // Métodos 'getters' que retornam as informações de sensação térmica com o sufixo "°C".
    public String getDay() {
        return day + "°C";  // Retorna a sensação térmica do dia com o sufixo "°C".
    }

    public String getMorn() {
        return morn + "°C";  // Retorna a sensação térmica da manhã com o sufixo "°C".
    }

    public String getEve() {
        return eve + "°C";  // Retorna a sensação térmica do entardecer com o sufixo "°C".
    }

    public String getNight() {
        return night + "°C";  // Retorna a sensação térmica da noite com o sufixo "°C".
    }
}
