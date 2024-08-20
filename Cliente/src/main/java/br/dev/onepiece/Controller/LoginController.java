package br.dev.onepiece.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.onepiece.Model.Usuario;
import br.dev.onepiece.Repository.UsuarioRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@PostMapping()
	public Usuario criarCliente(@RequestBody Usuario usuario) {
		Usuario u = usuarioRepository.findByLogin(usuario.getLogin());
		
		if (u == null)
			return ERRO
		else {
			if (usuario.equals(u.getSenha()))
				return CERTO
			else
				return ERRO;
		}
	}

}
