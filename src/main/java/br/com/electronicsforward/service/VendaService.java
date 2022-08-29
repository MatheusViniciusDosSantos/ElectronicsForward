package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Venda;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	private boolean existsById(Long id) {
		return vendaRepository.existsById(id);
	}
	
	public Venda findById(Long id) throws ResourceNotFoundException {
		Venda venda = vendaRepository.findById(id).orElse(null);
		
		if(venda == null) {
			throw new ResourceNotFoundException("Venda não encontrado com o id: " + id);
		} else {
			return venda;
		}
	}
	
	public Page<Venda> findAll(Pageable pageable) {
		return vendaRepository.findAll(pageable);
	}
	
	public Page<Venda> findAllByTipoPagamento(String tipo, Pageable page) {
		Page<Venda> vendas = vendaRepository.findByTipoPagamento(tipo, page);
		
		return vendas;
	}
	
	public Venda save(Venda venda) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(venda.getTipoPagamento())) {
			if(existsById(venda.getId())) {
				throw new ResourceAlreadyExistsException("Venda com id: " + venda.getId() + " já existe.");
			}
			venda.setStatus('A');
			venda.setDataCadastro(Calendar.getInstance().getTime());
			return vendaRepository.save(venda);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar venda");
			exe.addErrorMessage("Venda esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Venda venda) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(venda.getTipoPagamento())) {
			if (!existsById(venda.getId())) {
				throw new ResourceNotFoundException("Venda não encontrado com o id: " + venda.getId());
			}
			venda.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			vendaRepository.save(venda);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar venda");
			exe.addErrorMessage("Venda esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Venda não encontrado com o id: " + id);
		} else {
			vendaRepository.deleteById(id);
		}
	
	}  public Long count() {
		return vendaRepository.count();
	}
}
