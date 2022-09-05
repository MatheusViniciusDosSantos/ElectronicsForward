package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query(value = "select p from Cliente p where p.nome like %?1%")
	Page<Cliente> findByNome(String nome, Pageable page);
}

