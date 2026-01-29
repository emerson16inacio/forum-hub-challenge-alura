package com.emerson.forumhub.controller;

import com.emerson.forumhub.domain.usuario.DadosAutenticacao;
import com.emerson.forumhub.domain.usuario.Usuario;
import com.emerson.forumhub.infra.security.DadosTokenJWT;
import com.emerson.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService; // <--- Injetamos a classe que cria tokens

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        // Pega o usuário que logou e gera o token pra ele
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Devolve o token no JSON de resposta
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}