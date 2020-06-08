package io.github.com.kalilventura;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    /*
     * Busca no arquivo application.properties a chave com o valor correspondente e
     * injeta dentro da variavel
     */
    @Value("${spring.application.name}")
    private String applicationName;
}