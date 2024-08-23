package br.dev.onepiece.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.dev.onepiece.Model.Orcamento;
import br.dev.onepiece.Model.Projetista;
import br.dev.onepiece.Repository.ProjetistaRepository;
import br.dev.onepiece.Service.OrcamentoService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ProjetistaRepository projetistaRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Orcamento> salvarOrcamento(@RequestBody Orcamento orcamento) {
        if (orcamento == null) {
            return ResponseEntity.badRequest().build(); // Adiciona validação para orcamento nulo
        }

        if (orcamento.getProjetista() != null && orcamento.getProjetista().getIdPro() == null) {
            Projetista projetista = projetistaRepository.save(orcamento.getProjetista());
            orcamento.setProjetista(projetista);
        }
        Orcamento savedOrcamento = orcamentoService.salvar(orcamento);

        return ResponseEntity.ok(savedOrcamento);
    }
}
