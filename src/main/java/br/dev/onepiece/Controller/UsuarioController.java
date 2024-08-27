package br.dev.onepiece.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.onepiece.Model.Usuario;
import br.dev.onepiece.Repository.UsuarioRepository;




@RestController
@RequestMapping("/usuario")

public class UsuarioController {
	@Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping()
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @PostMapping()
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    @GetMapping("/{id}")
    public Usuario obterUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
    
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario novoUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(novoUsuario.getUsername());
                    usuario.setSenha(novoUsuario.getSenha());
                    usuario.setEmail(novoUsuario.getEmail());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
    
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
   
    
    

}