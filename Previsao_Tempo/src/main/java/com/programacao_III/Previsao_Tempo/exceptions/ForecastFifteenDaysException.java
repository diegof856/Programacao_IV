package com.programacao_III.Previsao_Tempo.exceptions;

// A classe 'ForecastFifteenDaysException' é uma exceção personalizada que estende a classe 'RuntimeException'.
// Ela é usada para indicar que houve um erro relacionado ao número de previsões do tempo solicitadas,
// especificamente quando o número de previsões ultrapassa o limite permitido (1 a 17 dias).
public class ForecastFifteenDaysException extends RuntimeException{

    // Construtor padrão que chama o construtor da classe mãe ('RuntimeException') com a mensagem
    // "A quantidade permitida de previsões é de 1 a 17". Esse construtor é útil quando você não deseja
    // fornecer uma mensagem personalizada e deseja usar a mensagem padrão.
    public ForecastFifteenDaysException(){
        super("A quantidade permitida de previsões é de 1 a 17");
    }

    // Construtor que permite passar uma mensagem personalizada ao criar a exceção.
    // Esse construtor é útil quando você quer fornecer uma mensagem de erro específica,
    // além da mensagem padrão fornecida pelo construtor sem parâmetros.
    public ForecastFifteenDaysException(String mensagem){
        super(mensagem);
    }
}
