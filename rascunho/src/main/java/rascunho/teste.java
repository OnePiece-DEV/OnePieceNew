import br.dev.onepiece.Repository.PedidoRepository; // Supondo que você tenha um PedidoRepository@RestController@RequestMapping("/cliente")publicclassClienteController {
    
    @Autowiredprivate ClienteRepository clienteRepository;
    
    @Autowiredprivate PedidoRepository pedidoRepository; // Adicione um repositório para verificar pedidos@GetMapping()public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    
    @PostMapping()public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @GetMapping("/{id}")public Cliente obterCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> newRuntimeException("Cliente não encontrado com id: " + id));
    }
    
    @PutMapping("/{id}")public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente novoCliente) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(novoCliente.getNome());
                    cliente.setCpf(novoCliente.getCpf());
                    cliente.setEmail(novoCliente.getEmail());
                    cliente.setTelefone(novoCliente.getTelefone());
                    cliente.setCep(novoCliente.getCep());
                    cliente.setLogradouro(novoCliente.getLogradouro());
                    cliente.setNumerolocal(novoCliente.getNumerolocal());
                    cliente.setCidade(novoCliente.getCidade());
                    cliente.setUF(novoCliente.getUF());
                    cliente.setComplemento(novoCliente.getComplemento());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> newRuntimeException("Cliente não encontrado com id: " + id));
    }
    
    @DeleteMapping("/{id}")publicvoiddeletarCliente(@PathVariable Long id) {
        Clientecliente= clienteRepository.findById(id)
                .orElseThrow(() -> newRuntimeException("Cliente não encontrado com id: " + id));
        
        // Verifique a condição antes de deletarbooleantemPedidos= pedidoRepository.existsByClienteId(id); // Supondo que exista um método no PedidoRepositoryif (temPedidos) {
            thrownewRuntimeException("Cliente não pode ser excluído porque está associado a um pedido.");
        }
        
        clienteRepository.deleteById(id);
    }
}
