package br.dev.onepiece.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @PostMapping
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario u = usuarioRepository.findByLogin(usuario.getLogin());
        
        if (u == null) {
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.UNAUTHORIZED);
        } else {
            if (usuario.getSenha().equals(u.getSenha())) {
                return new ResponseEntity<>("Login bem-sucedido", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Senha incorreta", HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
