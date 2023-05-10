package br.com.confidencial.CashFlowControl.domain.movimento;

import br.com.confidencial.CashFlowControl.domain.cliente.Cliente;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimentoCliente (Long idCliente,String valorOperacao){}
