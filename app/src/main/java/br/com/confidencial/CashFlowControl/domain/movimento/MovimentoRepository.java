package br.com.confidencial.CashFlowControl.domain.movimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

}
