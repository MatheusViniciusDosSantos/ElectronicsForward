package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Cliente;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private boolean existsById(Long id) {
		return clienteRepository.existsById(id);
	}
	
	public Cliente findById(Long id) throws ResourceNotFoundException {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if(cliente == null) {
			throw new ResourceNotFoundException("Cliente não encontrado com o id: " + id);
		} else {
			return cliente;
		}
	}
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public Page<Cliente> findAllByNome(String nome, Pageable page) {
		Page<Cliente> clientes = clienteRepository.findByNome(nome, page);
		
		return clientes;
	}
	
	public Cliente save(Cliente cliente) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(cliente.getNome())) {
			if(existsById(cliente.getId())) {
				throw new ResourceAlreadyExistsException("Cliente com id: " + cliente.getId() + " já existe.");
			}
			cliente.setStatus('A');
			cliente.setDataCadastro(Calendar.getInstance().getTime());
			return clienteRepository.save(cliente);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar cliente");
			exe.addErrorMessage("Cliente esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Cliente cliente) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(cliente.getNome())) {
			if (!existsById(cliente.getId())) {
				throw new ResourceNotFoundException("Cliente não encontrado com o id: " + cliente.getId());
			}
			cliente.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			clienteRepository.save(cliente);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar aluno");
			exe.addErrorMessage("Cliente esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Cliente não encontrado com o id: " + id);
		} else {
			clienteRepository.deleteById(id);
		}
	
	}  public Long count() {
		return clienteRepository.count();
	}
}
