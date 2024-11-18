package com.programacao_III.Previsao_Tempo.exceptions;

// A classe 'CityNotFoundException' é uma exceção personalizada que estende a classe 'RuntimeException'.
// Ela é usada para indicar que uma cidade não foi encontrada durante a execução do programa,
// geralmente quando o nome de uma cidade não pode ser encontrado em uma base de dados ou API.
public class CityNotFoundException extends RuntimeException{

    // Construtor padrão que chama o construtor da classe mãe ('RuntimeException') com a mensagem "Cidade não encontrada".
    // Este construtor pode ser usado quando você não tem uma mensagem personalizada para a exceção.
    public CityNotFoundException(){
        super("Cidade não encontrada");
    }

    // Construtor que permite passar uma mensagem personalizada ao criar a exceção.
    // Esse construtor é útil quando você quer fornecer uma mensagem específica para o erro,
    // além da mensagem padrão "Cidade não encontrada".
    public CityNotFoundException(String mensagem){
        super(mensagem);
    }
}
