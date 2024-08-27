package br.dev.onepiece.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.onepiece.Model.Orcamento;
import br.dev.onepiece.Repository.OrcamentoRepository;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findAll();
    }

    public void salvar(Orcamento orcamento) {
        orcamentoRepository.save(orcamento);
    }
}

