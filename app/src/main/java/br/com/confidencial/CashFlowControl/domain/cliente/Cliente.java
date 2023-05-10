package br.com.confidencial.CashFlowControl.domain.cliente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String saldo;

    public Cliente(DadosCliente dadosCliente){
        this.id = dadosCliente.idCliente();
        this.nome = dadosCliente.nome();
        this.email = dadosCliente.email();
        this.telefone = dadosCliente.telefone();
        this.cpf = dadosCliente.cpf();
        this.saldo = dadosCliente.saldo();
    }

}
