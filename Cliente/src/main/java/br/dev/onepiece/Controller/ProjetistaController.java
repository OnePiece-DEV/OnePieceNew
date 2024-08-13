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

import br.dev.onepiece.Model.Projetista;
import br.dev.onepiece.Repository.ProjetistaRepository;

@RestController
@RequestMapping("/projetista")

public class ProjetistaController {
	
	@Autowired
	private ProjetistaRepository projetistaRepository;
		
		@GetMapping()
		public List<Projetista> listarProjetistas() {
			return projetistaRepository.findAll();
			}
		
		@PostMapping()
		public Projetista criarProjetista(@RequestBody Projetista projetista) {
			return projetistaRepository.save(projetista);
		}
		
		@GetMapping("/{id}")
		public Projetista obterProjetista(@PathVariable Long id) {
			return projetistaRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Projetista não encontrado com id: " + id));
		}
		
		@PutMapping("/{id}")
		public Projetista atualizarProjetista (@PathVariable Long id, @RequestBody Projetista novoProjetista) {
			return projetistaRepository.findById(id)
					.map (projetista -> {
						projetista.setNome (novoProjetista.getNome());
						projetista.setCnpj (novoProjetista.getCnpj());
						projetista.setEmail (novoProjetista.getEmail());
						projetista.setTelefone (novoProjetista.getTelefone());
						projetista.setCep(novoProjetista.getCep());
						projetista.setEndereco(novoProjetista.getEndereco());
						return projetistaRepository.save(projetista);
					})
					.orElseThrow(() -> new RuntimeException("Projetista não encontrado com id: " + id));
		}
		
		@DeleteMapping("/{id}")
		public void deletarProjetista (@PathVariable Long id) {
			projetistaRepository.deleteById(id);
		}

}
