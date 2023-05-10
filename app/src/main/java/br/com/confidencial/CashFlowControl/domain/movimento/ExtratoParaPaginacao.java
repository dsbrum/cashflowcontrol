package br.com.confidencial.CashFlowControl.domain.movimento;

import java.time.LocalDateTime;

public record ExtratoParaPaginacao (Long idMov, String clientName, String valorMovimentacao, LocalDateTime data){
    public ExtratoParaPaginacao(Movimento movimento){
        this(movimento.getId(),movimento.getCliente().getNome(),movimento.getValor(),movimento.getData());
    }
}
