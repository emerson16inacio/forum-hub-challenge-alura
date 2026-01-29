package com.emerson.forumhub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails { // <--- O contrato assinado aqui

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    // Construtor manual que vamos usar depois
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // --- Métodos Obrigatórios do UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Diz quais "poderes" o usuário tem. Aqui dizemos que todo usuário é "ROLE_USER"
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha; // Ensina pro Spring que a senha está no campo 'senha'
    }

    @Override
    public String getUsername() {
        return login; // Ensina pro Spring que o usuário está no campo 'login'
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta não expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // A senha não expirou
    }

    @Override
    public boolean isEnabled() {
        return true; // O usuário está ativo
    }
}