package br.com.electronicsforward.controller;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.electronicsforward.domain.ItensCompra;
import br.com.electronicsforward.domain.ItensVenda;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.ItensVendaService;


 
@RestController
@RequestMapping("/api")
public class ItensVendaController {
   
    
    @Autowired
    private ItensVendaService itensVendaService;
    
    
    @GetMapping(value = "/itensVenda",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ItensVenda>> findAll(Pageable pageable) {           	
            return ResponseEntity.ok(itensVendaService.findAll(pageable));        
    }
 

    @GetMapping(value = "/itensVenda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItensVenda> findById(@PathVariable long id) {    	
        	ItensVenda itensVenda = itensVendaService.findById(id);
            return ResponseEntity.ok(itensVenda);        
    }
    
    @PostMapping(value = "/itensVenda")
    public ResponseEntity<ItensVenda> add(@RequestBody ItensVenda itensVenda) 
            throws URISyntaxException {        
        	ItensVenda itensVendaNovo = itensVendaService.save(itensVenda);
            return ResponseEntity.created(new URI("/api/itensVenda/" + itensVendaNovo.getId()))
                    .body(itensVendaNovo);      
    }
    
    @PutMapping(value = "/itensVenda/{id}")
    public ResponseEntity<ItensVenda> update(@Valid @RequestBody ItensVenda itensVenda, 
            @PathVariable long id) {       
            itensVenda.setId(id);
            itensVendaService.update(itensVenda);
            return ResponseEntity.ok().build();       
    }    
  
    @DeleteMapping(path="/itensVenda/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {        
            itensVendaService.deleteById(id);
            return ResponseEntity.ok().build();       
    }
    
    @GetMapping(value = "/itensVenda/venda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ItensVenda>> findByIdCompra(@PathVariable long id, Pageable pageable) throws ResourceNotFoundException {    	
    	Page<ItensVenda> itensVenda = itensVendaService.findByIdVenda(id, pageable);
            return ResponseEntity.ok(itensVenda);
    }
}