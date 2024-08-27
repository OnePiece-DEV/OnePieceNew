package br.dev.onepiece.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.onepiece.Model.Usuario;
import br.dev.onepiece.Repository.UsuarioRepository;

@Service
public class UserService {
	@Autowired
    private UsuarioRepository usuarioRepository;

	public Optional<Usuario> findUserByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
	}
	

	


	