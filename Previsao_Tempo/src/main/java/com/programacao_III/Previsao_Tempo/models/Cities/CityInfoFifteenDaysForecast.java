package com.programacao_III.Previsao_Tempo.models.Cities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe CityInfoFifteenDaysForecast é usada para representar informações sobre uma cidade,
// incluindo o nome da cidade, o nome do país, a população e a quantidade de pessoas.
// Esses dados são úteis para o contexto de uma previsão do tempo de 15 dias para a cidade.
// A classe é projetada para ser usada quando se lida com dados de cidade de uma API ou outro serviço.
public class CityInfoFifteenDaysForecast {

    // O campo 'cityName' armazena o nome da cidade.
    // A anotação @JsonProperty é usada para mapear o campo 'Cidade' no JSON para o atributo 'cityName' em Java.
    // A anotação @JsonAlias permite que o campo 'name' do JSON também seja mapeado para 'cityName' em Java.
    @JsonProperty("Cidade")
    @JsonAlias({"name"})
    private String cityName;

    // O campo 'countryName' armazena o nome do país onde a cidade está localizada.
    // A anotação @JsonProperty é usada para mapear o campo 'Pais' no JSON para o atributo 'countryName' em Java.
    // A anotação @JsonAlias também mapeia o campo 'country' do JSON para 'countryName' em Java.
    @JsonProperty("Pais")
    @JsonAlias({"country"})
    private String countryName;

    // O campo 'population' representa a população da cidade.
    // A anotação @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) significa que esse campo só
    // será usado na deserialização (quando o objeto é criado a partir de um JSON) e não aparecerá no JSON de resposta.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double population;

    // O campo 'quantityPopulation' armazena a quantidade de pessoas em formato de String.
    // Ele será mapeado para o campo 'Quantidade De Pessoas' no JSON de resposta.
    @JsonProperty("Quantidade De Pessoas")
    private String quantityPopulation;

    // Construtor padrão necessário para a deserialização do JSON.
    // O Jackson precisa de um construtor sem parâmetros para instanciar a classe durante a deserialização.
    public CityInfoFifteenDaysForecast() {}

    // Construtor parametrizado para inicializar os campos com valores fornecidos.
    // Esse construtor permite criar um objeto 'CityInfoFifteenDaysForecast' com dados específicos de cidade e país.
    public CityInfoFifteenDaysForecast(Double population, String countryName, String cityName) {
        this.population = population;
        this.countryName = countryName;
        this.cityName = cityName;
    }

    // Métodos getters para acessar os valores dos campos.
    // Eles são usados para acessar os dados da cidade e do país em outras partes do código.
    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Double getPopulation() {
        return population;
    }

    public String getQuantityPopulation() {
        return quantityPopulation;
    }

    // Método setter para 'quantityPopulation' que permite modificar a quantidade de pessoas em formato de String.
    public void setQuantityPopulation(String quantityPopulation) {
        this.quantityPopulation = quantityPopulation;
    }
}
