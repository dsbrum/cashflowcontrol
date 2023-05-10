package br.com.confidencial.CashFlowControl.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Override
    Optional<Cliente> findById(Long aLong);

/*    @Query("""
            SELECT id, nome, email, telefone, cpf, saldo
            FROM clientes c
            where
            c.id = id
            """)
    Boolean credita(Long id, String valor);
    @Query("""
            SELECT id, nome, email, telefone, cpf, saldo
            FROM clientes c
            where
            c.id = 
            """)
    Boolean Debita(String valor);*/


}
