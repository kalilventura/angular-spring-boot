package br.com.github.kalilventura.clientes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("{security.jwt.signing-key}")
    private String signingKey;

    @Bean
    public TokenStore tokenStore() {
        // Gerando tokens em memoria
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signingKey);
        return tokenConverter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // De onde vem os tokens
                .tokenStore(tokenStore())
                // Conversor de token
                .accessTokenConverter(accessTokenConverter())
                // O que foi configurado na outra classe
                .authenticationManager(authenticationManager);
    }

    // Sao as aplicacoes clientes que vao acessar o sistema
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // Login e senha para o client acessar nossa aplicação
                .withClient("my-angular-app").secret("@321")
                // O que a aplicação cliente poderá fazer
                .scopes("read", "write").authorizedGrantTypes("password")
                // Quanto tempo vai durar o token
                .accessTokenValiditySeconds(1800);
    }
}