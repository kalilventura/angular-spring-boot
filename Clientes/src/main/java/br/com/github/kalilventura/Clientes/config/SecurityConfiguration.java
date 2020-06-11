package br.com.github.kalilventura.clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import br.com.github.kalilventura.clientes.service.UsuarioService;

@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // Para utilizar os usuarios cadastrados no sistema
                .userDetailsService(userService)
                // Tipo de encoder para as senhas para verificacao
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // desabilita o csrf
                .cors().and() // volta para o http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // A aplicação não guarda
                                                                                             // sessão
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}