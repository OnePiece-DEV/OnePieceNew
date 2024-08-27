package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.onepiece.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByLogin(String login);
}
