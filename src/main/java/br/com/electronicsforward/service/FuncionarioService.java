package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Funcionario;
import br.com.electronicsforward.domain.Funcionario;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ClienteRepository;
import br.com.electronicsforward.repositoy.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private boolean existsById(Long id) {
		return funcionarioRepository.existsById(id);
	}
	
	public Funcionario findById(Long id) throws ResourceNotFoundException {
		Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
		
		if(funcionario == null) {
			throw new ResourceNotFoundException("Funcionario não encontrado com o id: " + id);
		} else {
			return funcionario;
		}
	}
	
	public Page<Funcionario> findAll(Pageable pageable) {
		return funcionarioRepository.findAll(pageable);
	}
	
	public Page<Funcionario> findAllByNome(String nome, Pageable page) {
		Page<Funcionario> funcionarios = funcionarioRepository.findByNome(nome, page);
		
		return funcionarios;
	}
	
	public Funcionario save(Funcionario funcionario) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(funcionario.getNome())) {
			if(existsById(funcionario.getId())) {
				throw new ResourceAlreadyExistsException("Funcionario com id: " + funcionario.getId() + " já existe.");
			}
			return funcionarioRepository.save(funcionario);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar aluno");
			exe.addErrorMessage("Funcionario esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Funcionario funcionario) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(funcionario.getNome())) {
			if (!existsById(funcionario.getId())) {
				throw new ResourceNotFoundException("Funcionario não encontrado com o id: " + funcionario.getId());
			}
			funcionarioRepository.save(funcionario);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar aluno");
			exe.addErrorMessage("Funcionario esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Funcionario não encontrado com o id: " + id);
		} else {
			funcionarioRepository.deleteById(id);
		}
	
	}  public Long count() {
		return funcionarioRepository.count();
	}
}
