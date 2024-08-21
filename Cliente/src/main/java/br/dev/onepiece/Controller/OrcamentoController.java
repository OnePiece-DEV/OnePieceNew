package br.dev.onepiece.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.dev.onepiece.Model.Orcamento;
import br.dev.onepiece.Service.OrcamentoService;

@Controller
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("orcamento", new Orcamento());
        return "orcamento/formulario";
    }

    @PostMapping("/salvar")
    public String salvarOrcamento(Orcamento orcamento) {
        orcamentoService.salvar(orcamento);
        return "redirect:/orcamento/lista";
    }

    @GetMapping("/lista")
    public String listarOrcamentos(Model model) {
        model.addAttribute("orcamentos", orcamentoService.listarTodos());
        return "orcamento/lista";
    }
}
