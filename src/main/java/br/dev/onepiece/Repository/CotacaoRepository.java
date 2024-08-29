package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.onepiece.Model.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

}
