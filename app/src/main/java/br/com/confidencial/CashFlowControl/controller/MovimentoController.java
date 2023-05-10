package br.com.confidencial.CashFlowControl.controller;


import br.com.confidencial.CashFlowControl.domain.movimento.MovimentoCliente;
import br.com.confidencial.CashFlowControl.domain.movimento.regra.MovimentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movimentos")
@SecurityRequirement(name = "bearer-key")
public class MovimentoController {

    @Autowired
    private MovimentoService service;
    @PostMapping
    @Transactional
    public ResponseEntity creditar(@RequestBody @Valid MovimentoCliente mc) {
        var dto = service.creditar(mc);
        return ResponseEntity.ok(dto);
    }
}
