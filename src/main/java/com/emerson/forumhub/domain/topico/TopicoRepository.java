package com.emerson.forumhub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

// <Topico, Long> significa: Esse repositório mexe com a entidade "Topico" e o ID dela é do tipo "Long"
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}