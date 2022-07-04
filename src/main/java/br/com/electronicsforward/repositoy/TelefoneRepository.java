package br.com.electronicsforward.repositoy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.electronicsforward.domain.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	@Query(value = "select p from Telefone p where p.funcionario.id like %?1%")
	Page<Telefone> findByIdFuncionario(long id, Pageable page);
}

