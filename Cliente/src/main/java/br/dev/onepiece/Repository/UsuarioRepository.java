package br.dev.onepiece.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.dev.onepiece.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
