package com.programacao_III.Previsao_Tempo.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(){
        super("Cidade não encontrada");
    }
    public CityNotFoundException(String mensagem){
        super(mensagem);
    }
}
