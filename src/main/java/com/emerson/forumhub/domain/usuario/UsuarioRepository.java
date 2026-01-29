package com.emerson.forumhub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico: O Spring cria o SQL "SELECT * FROM usuarios WHERE login = ?" sozinho
    UserDetails findByLogin(String login);
}