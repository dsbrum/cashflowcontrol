package br.com.confidencial.CashFlowControl.domain;

public class CheckRegraException extends RuntimeException {
    public CheckRegraException(String mensagem) {
        super(mensagem);
    }
}