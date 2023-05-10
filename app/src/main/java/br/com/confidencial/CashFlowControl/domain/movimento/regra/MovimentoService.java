package br.com.confidencial.CashFlowControl.domain.movimento.regra;


import br.com.confidencial.CashFlowControl.domain.CheckRegraException;
import br.com.confidencial.CashFlowControl.domain.cliente.Cliente;
import br.com.confidencial.CashFlowControl.domain.cliente.ClienteRepository;
import br.com.confidencial.CashFlowControl.domain.cliente.regra.ClienteService;
import br.com.confidencial.CashFlowControl.domain.movimento.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@Service
public class MovimentoService {
    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private ClienteService clienteService;

    public Optional<ExtratoSimplesMovimentacao> creditar(MovimentoCliente mc){
        checkValueOperation(mc);
        try{
            Optional<Cliente> clienteOptional = clienteService.findById(mc.idCliente());
            if(clienteOptional.isPresent()){
                var cliente = clienteOptional.get();
                var mvnt = new Movimento(null,cliente, mc.valorOperacao(),LocalDateTime.now());
                movimentoRepository.save(mvnt);
                var clientResultOperation = clienteService.increasesBalance(cliente, mc.valorOperacao());
                clientResultOperation.ifPresent(cliente1 -> log.info( String.format("Saldo do cliente id:%s foi atualizado!",cliente1.getId())));
                return Optional.of(new ExtratoSimplesMovimentacao(mvnt));
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CheckRegraException("Cliente não encontrado!");
        }
        return null;
    }


    public Optional<ExtratoSimplesMovimentacao> debitar(MovimentoCliente mc){
        checkValueOperation(mc);

        try{
            Optional<Cliente> clienteOptional = clienteService.findById(mc.idCliente());
            if(clienteOptional.isPresent()){
                var cliente = clienteOptional.get();
                var mvnt = new Movimento(null,cliente, mc.valorOperacao(), LocalDateTime.now());
                movimentoRepository.save(mvnt);
                var clientResultOperation = clienteService.decreasesBalance(cliente, mc.valorOperacao());
                clientResultOperation.ifPresent(cliente1 -> log.info( String.format("Saldo do cliente id:%s foi atualizado!",cliente1.getId())));
                return Optional.of(new ExtratoSimplesMovimentacao(mvnt));
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CheckRegraException("Cliente não encontrado!");
        }

        return null;
    }

    public Page<ExtratoParaPaginacao> consultaPaginada(Long idCliente, Pageable paginacao){
        Page<Movimento> movimentos = movimentoRepository.findByClienteId(idCliente, paginacao);
        return movimentos.map(ExtratoParaPaginacao::new);
    }



    private static void checkValueOperation(MovimentoCliente mc) {
        if(new BigDecimal(mc.valorOperacao()).compareTo(new BigDecimal("1"))<0){
            throw new CheckRegraException("Valor abaixo do permitido para movimentação!");
        }
    }
}
