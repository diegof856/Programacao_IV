package com.programacao_III.Previsao_Tempo.models.coord;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe Coord representa as coordenadas geográficas (longitude e latitude) de uma cidade,
// que são frequentemente retornadas em APIs de previsão do tempo para especificar a localização
// de onde as condições climáticas estão sendo obtidas.
public class Coord {

    // O campo 'lon' representa a longitude da cidade.
    // A anotação @JsonProperty é usada para mapear o campo no JSON para o nome do atributo 'lon' em Java.
    // A anotação @JsonAlias permite que o JSON utilize o nome 'lon' ou 'longitude' para o mesmo atributo.
    @JsonProperty("longitude")
    @JsonAlias({"lon"})
    private String lon;

    // O campo 'lat' representa a latitude da cidade.
    // A anotação @JsonProperty é usada para mapear o campo no JSON para o nome do atributo 'lat' em Java.
    // A anotação @JsonAlias permite que o JSON utilize o nome 'lat' ou 'latitude' para o mesmo atributo.
    @JsonProperty("latitude")
    @JsonAlias({"lat"})
    private String lat;

    // Construtor padrão necessário para a deserialização do JSON
    public Coord() {
    }

    // Construtor parametrizado para inicializar os campos com valores fornecidos
    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    // Métodos 'getters' para acessar os valores das coordenadas geográficas

    public String getLon() {
        return lon;  // Retorna o valor da longitude
    }

    public String getLat() {
        return lat;  // Retorna o valor da latitude
    }
}
