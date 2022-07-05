package br.com.electronicsforward.service;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.electronicsforward.domain.ItensVenda;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ItensVendaRepository;

@Service
public class ItensVendaService {
	
	@Autowired
	private ItensVendaRepository itensVendaRepository;
    
    private boolean existsById(Long id) {
        return itensVendaRepository.existsById(id);
    }
    
    public ItensVenda findById(Long id) {
    	ItensVenda itensVenda = itensVendaRepository.findById(id).orElse(null);
        return itensVenda;
    }
    
    public Page<ItensVenda> findAll(Pageable pageable) {
        
        return itensVendaRepository.findAll(pageable);
    }
   
    public ItensVenda save(ItensVenda itensVenda)  {
    	itensVenda.setDataCadastro(Calendar.getInstance().getTime());
    	return itensVendaRepository.save(itensVenda);
    }
    
    public void update(ItensVenda itensVenda) {      
    	itensVendaRepository.save(itensVenda);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
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
