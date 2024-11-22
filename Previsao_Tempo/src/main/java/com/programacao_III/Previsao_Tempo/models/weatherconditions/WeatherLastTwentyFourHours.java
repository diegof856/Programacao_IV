package com.programacao_III.Previsao_Tempo.models.weatherconditions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.climates.ClimateLastTwentyFourHours;
import com.programacao_III.Previsao_Tempo.models.winds.Wind;
import com.programacao_III.Previsao_Tempo.models.winds.WindLastTwentyFourHours;

import java.util.List;

// A classe WeatherLastTwentyFourHours representa as condições meteorológicas nas últimas 24 horas,
// incluindo as condições climáticas e do vento, além da data/hora da medição.
public class WeatherLastTwentyFourHours {

    // O campo 'climateLastTwentyFourHours' armazena as condições climáticas das últimas 24 horas.
    // O nome "Condições do clima" será usado ao serializar/deserializar o objeto JSON.
    // A anotação @JsonAlias permite que o campo também seja mapeado a partir do nome "main" no JSON.
    @JsonProperty("Condicoes_Do_Clima")
    @JsonAlias("main")
    private ClimateLastTwentyFourHours climateLastTwentyFourHours;

    // O campo 'dt' armazena a data e hora da medição, mas é usado apenas para gravação (não será incluído na serialização JSON).
    // O nome "dt" será usado para mapear o valor no JSON.
    // A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) indica que 'dt' não será lido ao deserializar o JSON, apenas gravado.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt"})
    private Long dt;

    // O campo 'wind' armazena as condições do vento das últimas 24 horas.
    // O nome "Condições do vento" será usado ao serializar/deserializar o objeto JSON.
    // A anotação @JsonAlias permite que o campo também seja mapeado a partir do nome "wind" no JSON.
    @JsonProperty("Condicoes_Do_Vento")
    @JsonAlias({"wind"})
    private WindLastTwentyFourHours wind;

    // Construtor que inicializa a classe WeatherLastTwentyFourHours com os valores de 'wind' e 'climateLastTwentyFourHours'.
    public WeatherLastTwentyFourHours(WindLastTwentyFourHours wind, ClimateLastTwentyFourHours climateLastTwentyFourHours) {
        this.wind = wind;
        this.climateLastTwentyFourHours = climateLastTwentyFourHours;
    }

    // Método getter que retorna o objeto 'climateLastTwentyFourHours' (condições climáticas das últimas 24 horas).
    public ClimateLastTwentyFourHours getClimateLastTwentyFourHours() {
        return climateLastTwentyFourHours;
    }

    // Método getter que retorna o objeto 'wind' (condições do vento das últimas 24 horas).
    public WindLastTwentyFourHours getWind() {
        return wind;
    }

    // Método getter para o campo 'dt', que representa a data e hora da medição.
    public Long getDt() {
        return dt;
    }

    // Método setter para definir o valor do campo 'dt'.
    public void setDt(Long dt) {
        this.dt = dt;
    }
}
