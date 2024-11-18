package com.programacao_III.Previsao_Tempo.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetData {

    // O RestTemplate é utilizado para realizar requisições HTTP no Spring.
    private final RestTemplate restTemplate;

    // O construtor da classe recebe uma instância de RestTemplate para inicializar a variável de instância.
    public GetData(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    // Este método genérico realiza uma requisição HTTP e retorna a resposta no tipo especificado.
    // Parâmetros:
    // - url: URL do endpoint a ser acessado.
    // - method: Método HTTP (GET, POST, PUT, DELETE, etc.).
    // - requestBody: Corpo da requisição (pode ser null se não houver corpo).
    // - responseType: Tipo da resposta esperada.
    // - headers: Cabeçalhos HTTP que devem ser enviados com a requisição.
    // Retorna: Um objeto ResponseEntity do tipo especificado, contendo a resposta da requisição.
    public <T> ResponseEntity<T> sendRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType, HttpHeaders headers){

        // Cria uma entidade HTTP (com cabeçalhos e corpo) que será enviada na requisição.
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        // Faz a requisição utilizando o RestTemplate e retorna a resposta como um ResponseEntity.
        return restTemplate.exchange(url, method, entity, responseType);
    }
}
