package br.dev.onepiece.Controller;

import br.dev.onepiece.Model.Cotacao;
import br.dev.onepiece.Model.Cliente;
import br.dev.onepiece.Repository.CotacaoRepository;
import br.dev.onepiece.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar uma nova cotação
    @PostMapping
    public ResponseEntity<Cotacao> criarCotacao(@RequestBody Cotacao cotacao) {
        if (cotacao.getCliente() == null || cotacao.getCliente().getId() == 0) {
            return ResponseEntity.badRequest().body(null); // Cliente não pode ser nulo
        }

        Optional<Cliente> cliente = clienteRepository.findById(cotacao.getCliente().getId());
        if (cliente.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Cliente não encontrado
        }

        cotacao.setCliente(cliente.get());
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
            return ResponseEntity.badRequest().body(null); // Cliente não encontrado
        }

        cotacaoAtualizada.setIdCotacao(id);
        cotacaoAtualizada.setCliente(cliente.get());
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
