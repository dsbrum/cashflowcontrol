package br.com.confidencial.CashFlowControl.domain.movimento;

public record ExtratoSimplesMovimentacao (Long idMov,String clientName,String valorMovimentacao,String saldo){
    public ExtratoSimplesMovimentacao(Movimento movimento){
        this(movimento.getId(),movimento.getCliente().getNome(),movimento.getValor(),movimento.getCliente().getSaldo());
    }
}
