package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.onepiece.Model.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {
    boolean existsByProjetista_IdPro(Long idPro);
}
