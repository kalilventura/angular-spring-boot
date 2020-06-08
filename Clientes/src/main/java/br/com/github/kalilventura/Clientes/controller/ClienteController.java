package br.com.github.kalilventura.clientes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.github.kalilventura.clientes.model.entity.Cliente;
import br.com.github.kalilventura.clientes.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    // Fara a injeção de dependencia
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        repository.findById(id).map(cliente -> {
            repository.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        repository.findById(id).map(cliente -> {
            clienteAtualizado.setId(cliente.getId());
            return repository.save(clienteAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}