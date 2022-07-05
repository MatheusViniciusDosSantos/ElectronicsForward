package br.com.electronicsforward.repositoy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.electronicsforward.domain.ItensCompra;

public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long>{

	@Query(value = "select p from ItensCompra p where p.compra.id like %?1%")
	Page<ItensCompra> findByIdCompra(Long id, Pageable page);
}
