package br.com.TrueUnion.TrueUnion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrueUnionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrueUnionApplication.class, args);
	}

}
