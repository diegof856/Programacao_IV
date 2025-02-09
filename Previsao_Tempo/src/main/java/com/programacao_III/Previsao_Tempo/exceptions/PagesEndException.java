package com.programacao_III.Previsao_Tempo.exceptions;


public class PagesEndException extends RuntimeException{


    public PagesEndException(){
        super("Não a mais previsões");
    }

    public PagesEndException(String mensagem){
        super(mensagem);
    }
}
