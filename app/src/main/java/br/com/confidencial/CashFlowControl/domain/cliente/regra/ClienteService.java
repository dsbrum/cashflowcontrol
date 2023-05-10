package br.com.confidencial.CashFlowControl.domain.cliente.regra;

import br.com.confidencial.CashFlowControl.domain.cliente.Cliente;
import br.com.confidencial.CashFlowControl.domain.cliente.ClienteRepository;
import br.com.confidencial.CashFlowControl.domain.cliente.DadosCliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> findById(Long id){
        return clienteRepository.findById(id);
    }
    public Optional<Cliente> saveCliente(Cliente cliente){
        Cliente save = clienteRepository.save(cliente);
        return Optional.of(save);
    }
    public Optional<Cliente> decreasesBalance(Cliente cliente,String valor){
        BigDecimal saldoAtualCliente = new BigDecimal(cliente.getSaldo());
        BigDecimal valorOperacao = new BigDecimal(valor);
        if(valorOperacao.compareTo(new BigDecimal("1"))<0){
            log.error("Valor da operacao nao pode ser menor que 1!");
            return Optional.empty();
        }
        if(saldoAtualCliente.compareTo(valorOperacao)<0){
            log.warn("Valor da operacao excede saldo do cliente!");
        }
        BigDecimal product = saldoAtualCliente.subtract(valorOperacao);
        Cliente save = clienteRepository.save(prepareClient(cliente, product));
        return Optional.of(save);
    }

    public Optional<Cliente> increasesBalance(Cliente cliente,String valor){
        BigDecimal saldoAtualCliente = new BigDecimal(cliente.getSaldo());
        BigDecimal valorOperacao = new BigDecimal(valor);
        if(valorOperacao.compareTo(new BigDecimal("1"))<0){
            log.error("Valor da operacao nao pode ser menor que 1!");
            return Optional.empty();
        }
        BigDecimal product = saldoAtualCliente.add(valorOperacao);
        Cliente save = clienteRepository.save(prepareClient(cliente, product));
        return Optional.of(save);
    }

    private static Cliente prepareClient(Cliente cliente, BigDecimal product) {
        Cliente clienteUpdate = new Cliente(new DadosCliente(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf(), product.toString()));
        return clienteUpdate;
    }

}
