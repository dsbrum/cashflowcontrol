package br.com.confidencial.CashFlowControl.domain.movimento.regra;


import br.com.confidencial.CashFlowControl.domain.CheckRegraException;
import br.com.confidencial.CashFlowControl.domain.cliente.Cliente;
import br.com.confidencial.CashFlowControl.domain.cliente.ClienteRepository;
import br.com.confidencial.CashFlowControl.domain.movimento.ExtratoSimplesMovimentacao;
import br.com.confidencial.CashFlowControl.domain.movimento.Movimento;
import br.com.confidencial.CashFlowControl.domain.movimento.MovimentoCliente;
import br.com.confidencial.CashFlowControl.domain.movimento.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MovimentoService {
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    public ExtratoSimplesMovimentacao creditar(MovimentoCliente mc){
        if(new BigDecimal(mc.valorOperacao()).compareTo(new BigDecimal("1"))<0){
            throw new CheckRegraException("Valor abaixo do permitido para movimentação!");
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(mc.idCliente());
        clienteOptional.ifPresentOrElse((cliente) -> {
                    var mvnt = new Movimento(null,cliente, mc.valorOperacao());
                    movimentoRepository.save(mvnt);
                },
                ()-> {throw new CheckRegraException("Cliente não encontrado!");});
        //var lastMvnt  = movimentoRepository.findLastOperationByClientId(mc.idCliente());
        return new ExtratoSimplesMovimentacao(null);
    }
}
