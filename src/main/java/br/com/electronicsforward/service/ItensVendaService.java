package br.com.electronicsforward.service;


import java.util.Calendar;

import br.com.electronicsforward.domain.ItensCompra;
import br.com.electronicsforward.domain.Produto;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.electronicsforward.domain.ItensVenda;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ItensVendaRepository;
import org.springframework.util.StringUtils;

@Service
public class ItensVendaService {
	
	@Autowired
	private ItensVendaRepository itensVendaRepository;
    
    private boolean existsById(Long id) {
        return itensVendaRepository.existsById(id);
    }

    public ItensVenda findById(Long id) throws ResourceNotFoundException {
        ItensVenda itensVenda = itensVendaRepository.findById(id).orElse(null);

        if(itensVenda == null) {
            throw new ResourceNotFoundException("Itens Venda não encontrado com o id: " + id);
        } else {
            return itensVenda;
        }
    }
    
    public Page<ItensVenda> findAll(Pageable pageable) {
        
        return itensVendaRepository.findAll(pageable);
    }

    public ItensVenda save(ItensVenda itensVenda) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(itensVenda.getValorVenda().toString())) {
            if(existsById(itensVenda.getId())) {
                throw new ResourceAlreadyExistsException("Itens Venda com id: " + itensVenda.getId() + " já existe.");
            }
            itensVenda.setStatus('A');
            itensVenda.setDataCadastro(Calendar.getInstance().getTime());
            return itensVendaRepository.save(itensVenda);
        } else {
            BadResourceException exe = new BadResourceException("Erro ao salvar itens da venda");
            exe.addErrorMessage("Itens Venda esta vazio ou nulo");
            throw exe;
        }
    }

    public void update(ItensVenda itensVenda) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(itensVenda.getValorVenda().toString())) {
            if (!existsById(itensVenda.getId())) {
                throw new ResourceNotFoundException("Itens Venda não encontrado com o id: " + itensVenda.getId());
            }
            itensVenda.setDataUltimaAlteracao(Calendar.getInstance().getTime());
            itensVendaRepository.save(itensVenda);
        } else {
            BadResourceException exe = new BadResourceException("Erro ao salvar itens da venda");
            exe.addErrorMessage("Itens Venda esta vazio ou nulo");
            throw exe;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Itens Venda não encontrado com o id: " + id);
        } else {
            itensVendaRepository.deleteById(id);
        }

    }
    
    public Long count() {
        return itensVendaRepository.count();
    }
    
    public Page<ItensVenda> findByIdVenda(Long id, Pageable page) throws ResourceNotFoundException {
		Page<ItensVenda> itensVenda = itensVendaRepository.findByIdVenda(id, page);
		
		if(itensVenda == null) {
			throw new ResourceNotFoundException("Itens da venda não encontrados com o id da venda: " + id);
		} else {
			return itensVenda;
		}
	}
}
