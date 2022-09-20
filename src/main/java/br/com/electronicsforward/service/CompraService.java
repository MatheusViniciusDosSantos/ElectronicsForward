package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Compra;
import br.com.electronicsforward.domain.ItensCompra;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.CompraRepository;
import br.com.electronicsforward.repositoy.ItensCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	private ItensCompraRepository itensCompraRepository;

	private boolean existsById(Long id) {
		return compraRepository.existsById(id);
	}
	
	public Compra findById(Long id) throws ResourceNotFoundException {
		Compra compra = compraRepository.findById(id).orElse(null);
		
		if(compra == null) {
			throw new ResourceNotFoundException("Compra não encontrado com o id: " + id);
		} else {
			return compra;
		}
	}
	
	public Page<Compra> findAll(Pageable pageable) {
		return compraRepository.findAll(pageable);
	}
	
	public Page<Compra> findAllByTipoPagamento(String tipo, Pageable page) {
		Page<Compra> compras = compraRepository.findByTipoPagamento(tipo, page);
		
		return compras;
	}
	
	public Compra save(Compra compra) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(compra.getTipoPagamento())) {
			if(existsById(compra.getId())) {
				throw new ResourceAlreadyExistsException("Compra com id: " + compra.getId() + " já existe.");
			}
			compra.setStatus('A');
			compra.setDataCadastro(Calendar.getInstance().getTime());
			return compraRepository.save(compra);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar compra");
			exe.addErrorMessage("Compra esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Compra compra) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(compra.getTipoPagamento())) {
			if (!existsById(compra.getId())) {
				throw new ResourceNotFoundException("Compra não encontrado com o id: " + compra.getId());
			}
			compra.setDataUltimaAlteracao(Calendar.getInstance().getTime());
			compraRepository.save(compra);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar compra");
			exe.addErrorMessage("Compra esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Compra não encontrado com o id: " + id);
		} else {
			compraRepository.deleteById(id);
		}
	
	}  public Long count() {
		return compraRepository.count();
	}

	public Compra calcularValorFinal(Compra compra, Pageable pegeable) {
		Page<ItensCompra> itensCompra = itensCompraRepository.findByIdCompra(compra.getId(), pegeable);
		Double valorTotal = 0.0;
		for (ItensCompra itenCompra: itensCompra) {
			itenCompra.setValorCusto(itenCompra.getProduto().getPreco() * itenCompra.getQuantidade());
			valorTotal += itenCompra.getValorCusto();
		}
		compra.setValorFinal(valorTotal * (1.0 - compra.getDesconto()));
		compraRepository.save(compra);
		return compra;
	}
}
