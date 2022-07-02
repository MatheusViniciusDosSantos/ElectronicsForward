package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.TipoPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {
	@Query(value = "select p from TipoPagamento p where p.tipo like %?1%")
	Page<TipoPagamento> findByTipo(String tipo, Pageable page);
}

