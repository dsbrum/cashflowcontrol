package br.com.confidencial.CashFlowControl.domain.movimento;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimentoCliente (Long idCliente,@NotNull LocalDateTime data,String valorOperacao){}
