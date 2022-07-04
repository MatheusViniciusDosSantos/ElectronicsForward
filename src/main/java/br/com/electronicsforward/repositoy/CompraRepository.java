package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.Compra;
import br.com.electronicsforward.domain.TipoPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	@Query(value = "select p from Compra p where p.tipoPagamento.tipo like %?1%")
	Page<Compra> findByTipoPagamento(String tipo, Pageable page);
}

