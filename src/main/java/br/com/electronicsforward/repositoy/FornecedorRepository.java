package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	@Query(value = "select p from Fornecedor p where p.nome like %?1%")
	Page<Fornecedor> findByNome(String nome, Pageable page);
}

