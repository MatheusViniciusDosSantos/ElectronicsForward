package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.TipoPagamento;
import br.com.electronicsforward.domain.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	@Query(value = "select p from Venda p where p.tipoPagamento.tipo like %?1%")
	Page<Venda> findByTipoPagamento(String tipo, Pageable page);
}

