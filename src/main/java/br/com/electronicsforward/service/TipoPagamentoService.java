package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Fornecedor;
import br.com.electronicsforward.domain.TipoPagamento;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.FornecedorRepository;
import br.com.electronicsforward.repositoy.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class TipoPagamentoService {
	
	@Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;
	
	private boolean existsById(Long id) {
		return tipoPagamentoRepository.existsById(id);
	}
	
	public TipoPagamento findById(Long id) throws ResourceNotFoundException {
		TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(id).orElse(null);
		
		if(tipoPagamento == null) {
			throw new ResourceNotFoundException("Tipo de Pagamento não encontrado com o id: " + id);
		} else {
			return tipoPagamento;
		}
	}
	
	public Page<TipoPagamento> findAll(Pageable pageable) {
		return tipoPagamentoRepository.findAll(pageable);
	}
	
	public Page<TipoPagamento> findAllByTipo(String tipo, Pageable page) {
		Page<TipoPagamento> tiposPagamento = tipoPagamentoRepository.findByTipo(tipo, page);
		
		return tiposPagamento;
	}
	
	public TipoPagamento save(TipoPagamento tipoPagamento) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(tipoPagamento.getTipo())) {
			if(existsById(tipoPagamento.getId())) {
				throw new ResourceAlreadyExistsException("Tipo de Pagamento com id: " + tipoPagamento.getId() + " já existe.");
			}
			tipoPagamento.setStatus('A');
			tipoPagamento.setDataCadastro(Calendar.getInstance().getTime());
			return tipoPagamentoRepository.save(tipoPagamento);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar tipo de pagamento");
			exe.addErrorMessage("Tipo de Pagamento esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(TipoPagamento tipoPagamento) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(tipoPagamento.getTipo())) {
			if (!existsById(tipoPagamento.getId())) {
				throw new ResourceNotFoundException("Tipo de Pagamento não encontrado com o id: " + tipoPagamento.getId());
			}
			tipoPagamento.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			tipoPagamentoRepository.save(tipoPagamento);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar tipo de pagamento");
			exe.addErrorMessage("Tipo de Pagamento esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Tipo de Pagamento não encontrado com o id: " + id);
		} else {
			tipoPagamentoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return tipoPagamentoRepository.count();
	}
}
