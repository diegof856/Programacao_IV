package com.programacao_III.Previsao_Tempo.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface IGetDateOrHour {
    default String pickUpOnlyTime(LocalDateTime dateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    return dateTime.toLocalTime().format(formatter);
}

    default String pickUpOnlyDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.toLocalDate().format(formatter);
    }

}
