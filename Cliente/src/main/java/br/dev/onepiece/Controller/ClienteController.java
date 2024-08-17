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

import br.dev.onepiece.Model.Cliente;
import br.dev.onepiece.Repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")

public class ClienteController {
		@Autowired
	private ClienteRepository clienteRepository;
		
		@GetMapping()
		public List<Cliente> listarClientes() {
			return clienteRepository.findAll();
			}
		
		@PostMapping()
		public Cliente criarCliente(@RequestBody Cliente cliente) {
			return clienteRepository.save(cliente);
		}
		
		@GetMapping("/{id}")
		public Cliente obterCliente(@PathVariable Long id) {
			return clienteRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
		}
		
		@PutMapping("/{id}")
		public Cliente atualizarCliente (@PathVariable Long id, @RequestBody Cliente novoCliente) {
			return clienteRepository.findById(id)
					.map (cliente -> {
						cliente.setNome (novoCliente.getNome());
						cliente.setCpf (novoCliente.getCpf());
						cliente.setEmail (novoCliente.getEmail());
						cliente.setTelefone (novoCliente.getTelefone());
						cliente.setCep(novoCliente.getCep());
						cliente.setLogradouro(novoCliente.getLogradouro());
						cliente.setNumerolocal(novoCliente.getNumerolocal());
						cliente.setCidade(novoCliente.getCidade());
						cliente.setUF(novoCliente.getUF());
						cliente.setComplemento (novoCliente.getComplemento());
						return clienteRepository.save(cliente);
					})
					.orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
		}
		
		@DeleteMapping("/{id}")
		public void deletarCliente (@PathVariable Long id) {
			clienteRepository.deleteById(id);
		}
}
