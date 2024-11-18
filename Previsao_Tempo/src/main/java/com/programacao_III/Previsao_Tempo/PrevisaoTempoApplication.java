package com.programacao_III.Previsao_Tempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PrevisaoTempoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrevisaoTempoApplication.class, args);
	}

}
