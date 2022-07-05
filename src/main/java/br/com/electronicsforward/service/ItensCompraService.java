package br.com.electronicsforward.service;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.electronicsforward.domain.ItensCompra;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ItensCompraRepository;

@Service
public class ItensCompraService {
	
	@Autowired
	private ItensCompraRepository itensCompraRepository;
    
    private boolean existsById(Long id) {
        return itensCompraRepository.existsById(id);
    }
    
    public ItensCompra findById(Long id) {
    	ItensCompra itensCompra = itensCompraRepository.findById(id).orElse(null);
        return itensCompra;
    }
    
    public Page<ItensCompra> findAll(Pageable pageable) {
        
        return itensCompraRepository.findAll(pageable);
    }
   
    public ItensCompra save(ItensCompra itensCompra)  {
    	itensCompra.setDataCadastro(Calendar.getInstance().getTime());
    	return itensCompraRepository.save(itensCompra);
    }
    
    public void update(ItensCompra itensCompra) {      
    	itensCompraRepository.save(itensCompra);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
        	itensCompraRepository.deleteById(id);
        }        
    }
    
    public Long count() {
        return itensCompraRepository.count();
    }
    
    public Page<ItensCompra> findByIdCompra(Long id, Pageable page) throws ResourceNotFoundException {
		Page<ItensCompra> itensCompra = itensCompraRepository.findByIdCompra(id, page);
		
		if(itensCompra == null) {
			throw new ResourceNotFoundException("Itens da Compra não encontrados com o id da Compra: " + id);
		} else {
			return itensCompra;
		}
	}
}
