package br.com.electronicsforward.service;

import br.com.electronicsforward.domain.Produto;
import br.com.electronicsforward.exception.BadResourceException;
import br.com.electronicsforward.exception.ResourceAlreadyExistsException;
import br.com.electronicsforward.exception.ResourceNotFoundException;
import br.com.electronicsforward.repositoy.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private boolean existsById(Long id) {
		return produtoRepository.existsById(id);
	}
	
	public Produto findById(Long id) throws ResourceNotFoundException {
		Produto produto = produtoRepository.findById(id).orElse(null);
		
		if(produto == null) {
			throw new ResourceNotFoundException("Produto não encontrado com o id: " + id);
		} else {
			return produto;
		}
	}
	
	public Page<Produto> findAll(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}
	
	public Page<Produto> findAllByNome(String nome, Pageable page) {
		Page<Produto> produtos = produtoRepository.findByNome(nome, page);
		
		return produtos;
	}
	
	public Produto save(Produto produto) throws BadResourceException, ResourceAlreadyExistsException {
		if(!StringUtils.isEmpty(produto.getNome())) {
			if(existsById(produto.getId())) {
				throw new ResourceAlreadyExistsException("Produto com id: " + produto.getId() + " já existe.");
			}
			return produtoRepository.save(produto);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar produto");
			exe.addErrorMessage("Produto esta vazio ou nulo");
			throw exe;
		}
		
		
	}
	
	public void update(Produto produto) throws BadResourceException, ResourceNotFoundException {
		if (!StringUtils.isEmpty(produto.getNome())) {
			if (!existsById(produto.getId())) {
				throw new ResourceNotFoundException("Produto não encontrado com o id: " + produto.getId());
			}
			produtoRepository.save(produto);
		} else {
			BadResourceException exe = new BadResourceException("Erro ao salvar produto");
			exe.addErrorMessage("Produto esta vazio ou nulo");
			throw exe;
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException {
		if(!existsById(id)) {
			throw new ResourceNotFoundException("Produto não encontrado com o id: " + id);
		} else {
			produtoRepository.deleteById(id);
		}
	
	}  public Long count() {
		return produtoRepository.count();
	}
}
