package br.com.github.kalilventura.clientes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.github.kalilventura.clientes.controller.dto.ServicoPrestadoDTO;
import br.com.github.kalilventura.clientes.model.entity.Cliente;
import br.com.github.kalilventura.clientes.model.entity.ServicoPrestado;
import br.com.github.kalilventura.clientes.model.repository.ClienteRepository;
import br.com.github.kalilventura.clientes.model.repository.ServicoRepository;
import br.com.github.kalilventura.clientes.utils.BigDecimalConverter;

@RestController
@RequestMapping("/api/servicos-prestados")
@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @Autowired
    public ServicoPrestadoController(ClienteRepository clienteRepository, ServicoRepository repository,
            BigDecimalConverter bigDecimalConverter) {
        this.clienteRepository = clienteRepository;
        this.repository = repository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "mes", required = false) Integer mes) {
        return repository.findClienteAndMes(nome, mes);
    }

}