package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.onepiece.Model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
