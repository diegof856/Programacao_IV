package com.programacao_III.Previsao_Tempo.models.cities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe CityInfo representa informações sobre uma cidade, como o nome da cidade,
// a quantidade de pessoas (população), a hora do nascer e pôr do sol. Ela é usada para
// encapsular esses dados que podem ser retornados em uma resposta de uma API ou utilizados
// em algum processamento interno.
public class CityInfo {

    // O campo 'cityName' representa o nome da cidade.
    // A anotação @JsonProperty é usada para mapear o campo 'Cidade' no JSON para o atributo 'cityName' em Java.
    // A anotação @JsonAlias mapeia o campo 'name' do JSON, permitindo que o atributo 'cityName' seja preenchido
    // também com o valor do campo 'name' do JSON, caso ele esteja presente.
    @JsonProperty("Cidade")
    @JsonAlias({"name"})
    private String cityName;

    // O campo 'population' representa a quantidade de pessoas na cidade.
    // A anotação @JsonProperty é usada para mapear o campo no JSON para o atributo 'population' em Java.
    // A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) especifica que o valor de 'population'
    // só pode ser usado para deserialização (leitura do JSON), mas não será incluído na saída JSON.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double population;

    // O campo 'quantityPopulation' é um campo adicional que representa a quantidade de pessoas,
    // mas como uma String. Ele é mapeado para o campo 'Quantidade De Pessoas' no JSON.
    @JsonProperty("Quantidade_De_Pessoas")
    private String quantityPopulation;

    // O campo 'sunrise' representa a hora do nascer do sol. Ele é mapeado para o campo 'sunrise' no JSON.
    // A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) indica que o campo 'sunrise'
    // só é utilizado durante a deserialização e não aparecerá no JSON de resposta.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long sunrise;

    // O campo 'sunset' representa a hora do pôr do sol. Ele é mapeado para o campo 'sunset' no JSON.
    // A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) é usada aqui também,
    // significando que este campo será excluído na resposta JSON, mas pode ser utilizado internamente.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long sunset;

    // O campo 'sunriseHour' armazena a hora formatada do nascer do sol.
    // Ele será mapeado para o campo 'Nascer do sol' no JSON de saída.
    @JsonProperty("Nascer_Do_Sol")
    private String sunriseHour;

    // O campo 'sunsetHour' armazena a hora formatada do pôr do sol.
    // Ele será mapeado para o campo 'Pôr Do Sol' no JSON de saída.
    @JsonProperty("Por_Do_Sol")
    private String sunsetHour;

    // Construtor padrão necessário para a deserialização do JSON
    public CityInfo() {}

    // Construtor parametrizado para inicializar os campos da classe com valores fornecidos.
    // Esse construtor permite criar um objeto 'CityInfo' com valores específicos para os campos.
    public CityInfo(Double population, Long sunset, Long sunrise, String cityName) {
        this.population = population;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.cityName = cityName;
    }

    // Métodos getters para acessar os valores dos campos. Eles são usados quando a classe
    // é manipulada em outras partes do código e para gerar a resposta JSON.
    public Double getPopulation() {
        return population;
    }

    public String getCityName() {
        return cityName;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public String getSunsetHour() {
        return sunsetHour;
    }

    // Método setter para 'sunsetHour' que permite definir a hora formatada do pôr do sol.
    public void setSunsetHour(String sunsetHour) {
        this.sunsetHour = sunsetHour;
    }

    public String getSunriseHour() {
        return sunriseHour;
    }

    // Método setter para 'sunriseHour' que permite definir a hora formatada do nascer do sol.
    public void setSunriseHour(String sunriseHour) {
        this.sunriseHour = sunriseHour;
    }

    public String getQuantityPopulation() {
        return quantityPopulation;
    }

    // Método setter para 'quantityPopulation' que permite definir a quantidade de pessoas como String.
    public void setQuantityPopulation(String quantityPopulation) {
        this.quantityPopulation = quantityPopulation;
    }
}
