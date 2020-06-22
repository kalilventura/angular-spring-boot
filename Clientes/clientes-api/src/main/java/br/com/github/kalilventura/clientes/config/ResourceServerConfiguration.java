package br.com.github.kalilventura.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Autorizar as rotas pelos roles
        http.authorizeRequests().antMatchers("/api/usuarios").permitAll() // todos acessam esse endpoint
                // os ** significa que tudo que estiver pra frente do /clientes/ pode ser
                // acessado caso esteja autenticado
                .antMatchers("/api/clientes/**", "/api/servicos-prestados/**").authenticated().anyRequest().denyAll();
    }
}