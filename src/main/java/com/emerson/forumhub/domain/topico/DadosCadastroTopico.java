package com.emerson.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank // Agora deve parar de ficar vermelho (se importou a dependência acima)
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}