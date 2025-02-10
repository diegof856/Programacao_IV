package com.programacao_III.Previsao_Tempo.interfaces;

import java.text.NumberFormat;
import java.util.Locale;

public interface IFormatMoreAttractive {
    default String transformFormatMoreAttractive(Double numberPopulation) {
        NumberFormat formatAttractive = NumberFormat.getInstance(new Locale("pt", "BR"));
        return formatAttractive.format(numberPopulation);
    }
}
