package br.dev.onepiece.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.dev.onepiece.Model.Projetista;
import br.dev.onepiece.Repository.ProjetistaRepository;
import br.dev.onepiece.Repository.OrcamentoRepository;

@RestController
@RequestMapping("/projetistas")
public class ProjetistaController {

    @Autowired
    private ProjetistaRepository projetistaRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

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
    public Projetista atualizarProjetista(@PathVariable Long id, @RequestBody Projetista novoProjetista) {
        return projetistaRepository.findById(id)
                .map(projetista -> {
                    projetista.setNome(novoProjetista.getNome());
                    projetista.setCnpj(novoProjetista.getCnpj());
                    projetista.setEmail(novoProjetista.getEmail());
                    projetista.setTelefone(novoProjetista.getTelefone());
                    projetista.setCep(novoProjetista.getCep());
                    projetista.setLogradouro(novoProjetista.getLogradouro());
                    projetista.setNumerolocal(novoProjetista.getNumerolocal());
                    projetista.setCidade(novoProjetista.getCidade());
                    projetista.setUF(novoProjetista.getUF());
                    projetista.setComplemento(novoProjetista.getComplemento());
                    return projetistaRepository.save(projetista);
                })
                .orElseThrow(() -> new RuntimeException("Projetista não encontrado com id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProjetista(@PathVariable Long id) {
        return projetistaRepository.findById(id)
                .map(projetista -> {
                    boolean temOrcamentosPendentes = orcamentoRepository.existsByProjetista_IdPro(id);
                    if (temOrcamentosPendentes) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Não é possível excluir o projetista, pois existem orçamentos em aberto.");
                    }
                    projetistaRepository.deleteById(id);
                    return ResponseEntity.ok("Projetista excluído com sucesso.");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Projetista não encontrado com id: " + id));
    }
}
