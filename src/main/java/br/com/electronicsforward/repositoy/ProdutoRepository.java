package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Query(value = "select p from Produto p where p.nome like %?1%")
	Page<Produto> findByNome(String nome, Pageable page);

	//Page<Produto> findAll(Pageable page);
}

