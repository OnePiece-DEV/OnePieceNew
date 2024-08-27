package br.dev.onepiece.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.onepiece.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

	Optional<Usuario> findByUsername(String username);
}
