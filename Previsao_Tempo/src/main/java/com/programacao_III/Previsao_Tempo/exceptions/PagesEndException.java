package com.programacao_III.Previsao_Tempo.exceptions;

// A classe 'PagesEndException' é uma exceção personalizada que estende a classe 'RuntimeException'.
// Ela é utilizada para indicar que não há mais previsões disponíveis, provavelmente em um cenário
// onde a navegação entre páginas de previsões é realizada e a última página foi alcançada.
public class PagesEndException extends RuntimeException{

    // Construtor padrão que chama o construtor da classe mãe ('RuntimeException') com a mensagem
    // "Não a mais previsões". Esse construtor é útil quando você não deseja fornecer uma mensagem personalizada.
    // A mensagem padrão informa que não há mais previsões disponíveis para exibir.
    public PagesEndException(){
        super("Não a mais previsões");
    }

    // Construtor que permite passar uma mensagem personalizada ao criar a exceção.
    // Esse construtor é útil quando você quer fornecer uma mensagem de erro mais detalhada ou específica.
    // Ele passa a mensagem fornecida ao construtor da classe mãe ('RuntimeException').
    public PagesEndException(String mensagem){
        super(mensagem);
    }
}
