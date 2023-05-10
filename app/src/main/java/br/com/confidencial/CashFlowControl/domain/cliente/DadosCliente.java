package br.com.confidencial.CashFlowControl.domain.cliente;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCliente (Long idCliente,
                            @NotNull String nome,//
                            String email,//
                            String telefone,//
                            String cpf,//
                            String saldo){}//
