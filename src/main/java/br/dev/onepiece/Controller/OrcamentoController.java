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
import br.dev.onepiece.Model.Orcamento;
import br.dev.onepiece.Model.Projetista;
import br.dev.onepiece.Repository.ClienteRepository;
import br.dev.onepiece.Repository.ProjetistaRepository;
import br.dev.onepiece.Service.OrcamentoService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ProjetistaRepository projetistaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Orcamento> salvarOrcamento(@RequestBody Orcamento orcamento) {
        if (orcamento == null || orcamento.getCliente() == null) {
            return ResponseEntity.badRequest().build(); // Adiciona validação para orcamento nulo
        }

        // Verifica e associa o cliente
        Cliente cliente = clienteRepository.findById(orcamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + orcamento.getCliente().getId()));
        orcamento.setCliente(cliente);

        // Verifica e associa o projetista se necessário
        if (orcamento.getProjetista() != null && orcamento.getProjetista().getIdPro() == null) {
            Projetista projetista = projetistaRepository.save(orcamento.getProjetista());
            orcamento.setProjetista(projetista);
        }

        // Salva o orçamento com o cliente e projetista (se aplicável) associados
        Orcamento savedOrcamento = orcamentoService.salvar(orcamento);

        return ResponseEntity.ok(savedOrcamento);
    }

    @GetMapping
    public ResponseEntity<List<Orcamento>> listarOrcamentos() {
        List<Orcamento> orcamentos = orcamentoService.listarTodos();
        return ResponseEntity.ok(orcamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscarOrcamentoPorId(@PathVariable Long id) {
        Optional<Orcamento> orcamento = orcamentoService.buscarPorId(id);
        return orcamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orcamento> atualizarOrcamento(@PathVariable Long id, @RequestBody Orcamento orcamentoAtualizado) {
        Optional<Orcamento> orcamentoOptional = orcamentoService.buscarPorId(id);
        if (!orcamentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Orcamento orcamentoExistente = orcamentoOptional.get();
        if (orcamentoAtualizado.getProjetista() != null && orcamentoAtualizado.getProjetista().getIdPro() == null) {
            Projetista projetista = projetistaRepository.save(orcamentoAtualizado.getProjetista());
            orcamentoExistente.setProjetista(projetista);
        } else {
            orcamentoExistente.setProjetista(orcamentoAtualizado.getProjetista());
        }
        orcamentoExistente.setDescricao(orcamentoAtualizado.getDescricao());
        orcamentoExistente.setValor(orcamentoAtualizado.getValor());

        Orcamento orcamentoAtualizadoSalvo = orcamentoService.salvar(orcamentoExistente);

        return ResponseEntity.ok(orcamentoAtualizadoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        Optional<Orcamento> orcamentoOptional = orcamentoService.buscarPorId(id);
        if (!orcamentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        orcamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
