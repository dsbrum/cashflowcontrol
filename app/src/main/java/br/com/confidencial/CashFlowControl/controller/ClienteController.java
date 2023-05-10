package br.com.confidencial.CashFlowControl.controller;


import br.com.confidencial.CashFlowControl.domain.cliente.Cliente;
import br.com.confidencial.CashFlowControl.domain.cliente.ClienteRepository;
import br.com.confidencial.CashFlowControl.domain.cliente.DadosCliente;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCliente dados) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
