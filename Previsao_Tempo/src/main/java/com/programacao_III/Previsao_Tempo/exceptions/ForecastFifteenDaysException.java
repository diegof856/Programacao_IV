package com.programacao_III.Previsao_Tempo.exceptions;


public class ForecastFifteenDaysException extends RuntimeException{


    public ForecastFifteenDaysException(){
        super("A quantidade permitida de previsões é de 1 a 17");
    }

    public ForecastFifteenDaysException(String mensagem){
        super(mensagem);
    }
}
