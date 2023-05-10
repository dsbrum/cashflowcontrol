package br.com.confidencial.CashFlowControl.controller;


import br.com.confidencial.CashFlowControl.domain.movimento.ExtratoParaPaginacao;
import br.com.confidencial.CashFlowControl.domain.movimento.ExtratoSimplesMovimentacao;
import br.com.confidencial.CashFlowControl.domain.movimento.Movimento;
import br.com.confidencial.CashFlowControl.domain.movimento.MovimentoCliente;
import br.com.confidencial.CashFlowControl.domain.movimento.regra.MovimentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movimentos")
@SecurityRequirement(name = "bearer-key")
public class MovimentoController {

    @Autowired
    private MovimentoService service;

    @PostMapping("/creditar")
    @Transactional
    public ResponseEntity creditar(@RequestBody @Valid MovimentoCliente mc) {
        var dto = service.creditar(mc);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }
    @PostMapping("/debitar")
    @Transactional
    public ResponseEntity debitar(@RequestBody @Valid MovimentoCliente mc) {
        var dto = service.debitar(mc);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }
    @GetMapping("/client/{id}")
    @Transactional
    public Page<ExtratoParaPaginacao> consultar(@PathVariable Long id,
                                                @PageableDefault(sort = "data_operacao",//
                                                        direction = Sort.Direction.DESC, //
                                                        page = 0,//
                                                        size = 10) Pageable paginacao) {//

        return service.consultaPaginada(id, paginacao);
    }
}
