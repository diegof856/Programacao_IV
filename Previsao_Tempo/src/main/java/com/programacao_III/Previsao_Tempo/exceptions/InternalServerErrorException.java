package com.programacao_III.Previsao_Tempo.exceptions;

public class InternalServerErrorException extends RuntimeException{

public InternalServerErrorException(){
        super("Erro interno do servidor!!");
    }

    public InternalServerErrorException(String mensagem){
        super(mensagem);
    }
}
