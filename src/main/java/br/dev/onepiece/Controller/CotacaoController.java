package br.dev.onepiece.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.onepiece.Model.Cliente;
import br.dev.onepiece.Model.Cotacao;
import br.dev.onepiece.Model.Projetista;
import br.dev.onepiece.Repository.ClienteRepository;
import br.dev.onepiece.Repository.CotacaoRepository;
import br.dev.onepiece.Repository.ProjetistaRepository;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProjetistaRepository projetistaRepository;

    // Criar uma nova cotação
    @PostMapping
    public ResponseEntity<Cotacao> criarCotacao(@RequestBody Cotacao cotacao) {
        if (cotacao.getCliente() == null || cotacao.getCliente().getId() == null) {
            return ResponseEntity.badRequest().build(); // Cliente não pode ser nulo
        }

        if (cotacao.getProjetista() == null || cotacao.getProjetista().getIdPro() == null) {
            return ResponseEntity.badRequest().build(); // Projetista não pode ser nulo
        }

        Optional<Cliente> cliente = clienteRepository.findById(cotacao.getCliente().getId());
        if (cliente.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Cliente não encontrado
        }

        Optional<Projetista> projetista = projetistaRepository.findById(cotacao.getProjetista().getIdPro());
        if (projetista.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Projetista não encontrado
        }

        cotacao.setCliente(cliente.get());
        cotacao.setProjetista(projetista.get());
        Cotacao novaCotacao = cotacaoRepository.save(cotacao);
        return ResponseEntity.ok(novaCotacao);
    }

    // Buscar todas as cotações
    @GetMapping
    public List<Cotacao> listarCotacoes() {
        return cotacaoRepository.findAll();
    }

    // Buscar uma cotação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cotacao> obterCotacao(@PathVariable Long id) {
        Optional<Cotacao> cotacao = cotacaoRepository.findById(id);
        if (cotacao.isPresent()) {
            return ResponseEntity.ok(cotacao.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar uma cotação existente
    @PutMapping("/{id}")
    public ResponseEntity<Cotacao> atualizarCotacao(@PathVariable Long id, @RequestBody Cotacao cotacaoAtualizada) {
        if (!cotacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Cliente> cliente = clienteRepository.findById(cotacaoAtualizada.getCliente().getId());
        if (cliente.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Cliente não encontrado
        }

        Optional<Projetista> projetista = projetistaRepository.findById(cotacaoAtualizada.getProjetista().getIdPro());
        if (projetista.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Projetista não encontrado
        }

        cotacaoAtualizada.setIdCotacao(id);
        cotacaoAtualizada.setCliente(cliente.get());
        cotacaoAtualizada.setProjetista(projetista.get());
        Cotacao cotacao = cotacaoRepository.save(cotacaoAtualizada);
        return ResponseEntity.ok(cotacao);
    }

    // Excluir uma cotação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCotacao(@PathVariable Long id) {
        if (!cotacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cotacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
