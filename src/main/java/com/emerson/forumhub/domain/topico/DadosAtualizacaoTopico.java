package com.emerson.forumhub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull // O ID é obrigatório pra saber QUAL tópico atualizar
        Long id,
        String titulo,
        String mensagem
) {
}