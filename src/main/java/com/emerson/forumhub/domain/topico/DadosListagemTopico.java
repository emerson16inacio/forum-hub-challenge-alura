package com.emerson.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, EstadoTopico status, String autor, String curso) {

    // Criamos um construtor que recebe o Topico e já extrai os dados
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}