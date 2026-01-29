package com.emerson.forumhub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos") // Diz ao banco que a tabela se chamará 'topicos'
@Entity(name = "Topico") // Nome da entidade no Java
@Getter // O Lombok cria os métodos getTitulo(), getMensagem() etc. automaticamente
@NoArgsConstructor // Cria um construtor vazio (exigência do JPA)
@AllArgsConstructor // Cria um construtor com todos os atributos
@EqualsAndHashCode(of = "id") // Para comparar tópicos pelo ID
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco vai gerar o ID (1, 2, 3...)
    private Long id;

    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now(); // Preenche a data atual automaticamente

    @Enumerated(EnumType.STRING) // Salva o texto "NAO_RESPONDIDO" no banco, não o número
    private EstadoTopico status = EstadoTopico.NAO_RESPONDIDO;

    private String autor;
    private String curso;

    // Construtor personalizado para recebermos os dados do DTO
    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }
}
