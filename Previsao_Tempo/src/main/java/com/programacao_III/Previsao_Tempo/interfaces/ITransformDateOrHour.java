package com.programacao_III.Previsao_Tempo.interfaces;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public interface ITransformDateOrHour {

    default String transformTimesTampHour(Long timesTamp) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }

    default String transformTimesTampDateHour(Long timesTamp) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }

    default LocalDateTime transformDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
