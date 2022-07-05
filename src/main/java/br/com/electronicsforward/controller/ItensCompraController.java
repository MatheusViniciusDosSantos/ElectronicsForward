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
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.service.ItensCompraService;


 
@RestController
@RequestMapping("/api")
public class ItensCompraController {
   
    
    @Autowired
    private ItensCompraService itensCompraService;
    
    
    @GetMapping(value = "/itensCompra",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ItensCompra>> findAll(Pageable pageable) {           	
            return ResponseEntity.ok(itensCompraService.findAll(pageable));        
    }
 

    @GetMapping(value = "/itensCompra/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItensCompra> findById(@PathVariable long id) {    	
        	ItensCompra itensCompra = itensCompraService.findById(id);
            return ResponseEntity.ok(itensCompra);        
    }
    
    @PostMapping(value = "/itensCompra")
    public ResponseEntity<ItensCompra> add(@RequestBody ItensCompra itensCompra) 
            throws URISyntaxException {        
        	ItensCompra itensCompraNovo = itensCompraService.save(itensCompra);
            return ResponseEntity.created(new URI("/api/itensCompra/" + itensCompraNovo.getId()))
                    .body(itensCompraNovo);      
    }
    
    @PutMapping(value = "/itensCompra/{id}")
    public ResponseEntity<ItensCompra> update(@Valid @RequestBody ItensCompra itensCompra, 
            @PathVariable long id) {       
            itensCompra.setId(id);
            itensCompraService.update(itensCompra);
            return ResponseEntity.ok().build();       
    }    
  
    @DeleteMapping(path="/itensCompra/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {        
            itensCompraService.deleteById(id);
            return ResponseEntity.ok().build();       
    }
    
    @GetMapping(value = "/itensCompra/compra/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ItensCompra>> findByIdCompra(@PathVariable long id, Pageable pageable) throws ResourceNotFoundException {    	
    	Page<ItensCompra> itensCompra = itensCompraService.findByIdCompra(id, pageable);
            return ResponseEntity.ok(itensCompra);
    }
}