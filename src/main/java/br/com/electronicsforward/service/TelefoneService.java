package br.com.electronicsforward.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.electronicsforward.domain.Telefone;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.TelefoneRepository;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	private boolean existsById(Long id) {
		return telefoneRepository.existsById(id);
	}
	
	public Telefone findById(Long id) throws ResourceNotFoundException {
		Telefone telefone = telefoneRepository.findById(id).orElse(null);
		
		if(telefone == null) {
			throw new ResourceNotFoundException("Telefone não encontrado com o id: " + id);
		} else {
			return telefone;
		}
	}
	
	public Page<Telefone> findAll(Pageable pageable) {
		return telefoneRepository.findAll(pageable);
	}
	
	public Page<Telefone> findAllByIdFuncionario(long id, Pageable page) {
		Page<Telefone> telefones = telefoneRepository.findByIdFuncionario(id, page);
		
		return telefones;
	}
	
	public Telefone save(Telefone telefone) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(telefone.getNumero())) {
			if(existsById(telefone.getId())) {
				throw new ResourceAlreadyExistsException("Telefone com id: " + telefone.getId() + " já existe.");
			}
			telefone.setStatus('A');
			telefone.setDataCadastro(Calendar.getInstance().getTime());
			return telefoneRepository.save(telefone);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar telefone");
			exe.addErrorMessage("Telefone esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Telefone telefone) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(telefone.getNumero())) {
			if (!existsById(telefone.getId())) {
				throw new ResourceNotFoundException("Telefone não encontrado com o id: " + telefone.getId());
			}
			telefone.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			telefoneRepository.save(telefone);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar telefone");
			exe.addErrorMessage("Telefone esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Telefone não encontrado com o id: " + id);
		} else {
			telefoneRepository.deleteById(id);
		}
	
	}  public Long count() {
		return telefoneRepository.count();
	}
}
