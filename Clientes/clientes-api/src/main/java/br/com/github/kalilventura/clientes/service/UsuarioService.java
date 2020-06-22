package br.com.github.kalilventura.clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.github.kalilventura.clientes.model.entity.Usuario;
import br.com.github.kalilventura.clientes.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Não encontrado"));

        // Criando uma instancia de userDetails
        return User.builder().username(usuario.getUsername()).password(usuario.getPassword()).roles("USER").build();
    }

}