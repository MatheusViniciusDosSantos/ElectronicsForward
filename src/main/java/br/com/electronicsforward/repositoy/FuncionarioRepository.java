package br.com.electronicsforward.repositoy;

import br.com.electronicsforward.domain.Cliente;
import br.com.electronicsforward.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	@Query(value = "select p from Funcionario p where p.nome like %?1%")
	Page<Funcionario> findByNome(String nome, Pageable page);
}

