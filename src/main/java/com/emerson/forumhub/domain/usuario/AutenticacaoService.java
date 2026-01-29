package com.emerson.forumhub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Diz pro Spring: "Isso é um serviço, carregue ele!"
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Toda vez que alguém tentar logar, o Spring pede pra buscar o usuário pelo login aqui
        return repository.findByLogin(username);
    }
}