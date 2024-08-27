package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.onepiece.Model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
}
