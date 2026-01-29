package com.emerson.forumhub.controller;

import com.emerson.forumhub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController // Diz pro Spring: "Eu sou um controlador, escuto requisições!"
@RequestMapping("topicos") // Diz: "Escuto tudo que chegar em /topicos"
public class TopicoController {

    @Autowired // Injeção de Dependências: O Spring te dá o repositório pronto pra usar
    private TopicoRepository repository;

    @PostMapping // Diz: "Quando chegar um pedido POST, execute esse método"
    @Transactional // Diz: "Isso é uma transação no banco. Se der erro, desfaz tudo."
    public void cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        // 1. Recebemos os dados do JSON (DTO)
        // 2. Convertemos para a Entidade Topico
        Topico topico = new Topico(dados);

        // 3. Salvamos no banco usando o repositório
        repository.save(topico);

        // Só pra gente ver no console que funcionou
        System.out.println("Tópico recebido: " + dados.titulo());
    }
    @GetMapping // Diz: "Quando chegar um pedido GET, execute isso aqui"
    public Page<DadosListagemTopico> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        // 1. O repository busca tudo, mas fatiado em páginas (paginacao)
        // 2. O .map converte cada Topico (entidade) em DadosListagemTopico (DTO)
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }
    // 1. ATUALIZAR (PUT)
    @PutMapping
    @Transactional // Importante! Garante que o JPA salve as alterações automaticamente
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(dados.id()); // Busca o tópico pelo ID
        topico.atualizarInformacoes(dados); // Atualiza os dados na memória
        // Graças ao @Transactional, o Spring "commita" (salva) as mudanças no final do método automaticamente (Dirty Checking)
    }

    // 2. EXCLUIR (DELETE)
    @DeleteMapping("/{id}") // O ID vem na URL (ex: /topicos/1)
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id); // O método mágico que apaga do banco
    }

    // 3. DETALHAR (GET com ID)
    @GetMapping("/{id}")
    public DadosListagemTopico detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id); // Carrega o tópico
        return new DadosListagemTopico(topico); // Transforma em DTO e devolve
    }
}