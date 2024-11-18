package com.programacao_III.Previsao_Tempo.exceptions;

// A classe 'InternalServerErrorException' é uma exceção personalizada que estende a classe 'RuntimeException'.
// Ela é usada para indicar que ocorreu um erro interno no servidor. Esse tipo de erro geralmente está relacionado
// a problemas imprevistos no servidor, como falhas no processamento ou no acesso a recursos essenciais.
public class InternalServerErrorException extends RuntimeException{

    // Construtor padrão que chama o construtor da classe mãe ('RuntimeException') com a mensagem
    // "Erro interno do servidor!!". Esse construtor é útil quando você não deseja fornecer uma mensagem personalizada.
    // A mensagem padrão informa que houve um erro inesperado no servidor, mas sem detalhes específicos.
    public InternalServerErrorException(){
        super("Erro interno do servidor!!");
    }

    // Construtor que permite passar uma mensagem personalizada ao criar a exceção.
    // Esse construtor é útil quando você quer fornecer uma mensagem de erro mais detalhada ou específica.
    // Ele passa a mensagem fornecida ao construtor da classe mãe ('RuntimeException').
    public InternalServerErrorException(String mensagem){
        super(mensagem);
    }
}
