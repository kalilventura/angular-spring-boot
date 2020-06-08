package br.com.github.kalilventura.clientes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

	@Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("Executando com sucesso");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
