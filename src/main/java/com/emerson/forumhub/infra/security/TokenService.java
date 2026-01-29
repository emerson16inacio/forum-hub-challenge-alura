package com.emerson.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.emerson.forumhub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}") // Lê a senha lá do application.properties
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API ForumHub") // Quem está emitindo o token
                    .withSubject(usuario.getLogin()) // Quem é o dono do token (o usuário logado)
                    .withExpiresAt(dataExpiracao()) // Quando o token vence
                    .sign(algoritmo); // Assina digitalmente
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        // Define que o token vale por 2 horas a partir de agora
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    // Adicione isso dentro do TokenService
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API ForumHub")
                    .build()
                    .verify(tokenJWT) // Isso aqui valida se o token é válido e não expirou
                    .getSubject(); // Devolve o e-mail do usuário dono do token
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}