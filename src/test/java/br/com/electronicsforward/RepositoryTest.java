package br.com.electronicsforward;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.electronicsforward.repositoy.ClienteRepository;
import br.com.electronicsforward.repositoy.CompraRepository;
import br.com.electronicsforward.repositoy.FornecedorRepository;
import br.com.electronicsforward.repositoy.FuncionarioRepository;
import br.com.electronicsforward.repositoy.MarcaRepository;
import br.com.electronicsforward.repositoy.ProdutoRepository;
import br.com.electronicsforward.repositoy.TelefoneRepository;
import br.com.electronicsforward.repositoy.TipoPagamentoRepository;
import br.com.electronicsforward.repositoy.VendaRepository;

@SpringBootTest
public class RepositoryTest {
    @Autowired
	private VendaRepository vendaRepository;

    @Autowired
	private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
	private CompraRepository compraRepository;

    @Autowired
	private FornecedorRepository fornecedorRepository;

    @Autowired
	private ClienteRepository clienteRepository;

    @Autowired
	private FuncionarioRepository funcionarioRepository;

    @Autowired
	private MarcaRepository marcaRepository;

    @Autowired
	private TelefoneRepository telefoneRepository;

    @Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void vendaRepositoryNotNull() throws Exception {
		assertThat(vendaRepository).isNotNull();
	}

    @Test
	public void tipoPagamentoRepositoryNotNull() throws Exception {
		assertThat(tipoPagamentoRepository).isNotNull();
	}

    @Test
	public void compraRepositoryNotNull() throws Exception {
		assertThat(compraRepository).isNotNull();
	}

    @Test
	public void fornecedorRepositoryNotNull() throws Exception {
		assertThat(fornecedorRepository).isNotNull();
	}
    
    @Test
	public void clienteRepositoryNotNull() throws Exception {
		assertThat(clienteRepository).isNotNull();
	}
    
    @Test
	public void funcionarioRepositoryNotNull() throws Exception {
		assertThat(funcionarioRepository).isNotNull();
	}
    
    @Test
	public void marcaRepositoryNotNull() throws Exception {
		assertThat(marcaRepository).isNotNull();
	}
    
    @Test
	public void telefoneRepositoryNotNull() throws Exception {
		assertThat(telefoneRepository).isNotNull();
	}
    
    @Test
	public void produtoRepositoryNotNull() throws Exception {
		assertThat(produtoRepository).isNotNull();
	}

    @Test
	public void vendaRepositoryIsInstance() throws Exception {
		assertThat(vendaRepository).isInstanceOf(VendaRepository.class);
	}

    @Test
	public void tipoPagamentoRepositoryIsInstance() throws Exception {
		assertThat(tipoPagamentoRepository).isInstanceOf(TipoPagamentoRepository.class);
	}

    @Test
	public void compraRepositoryIsInstance() throws Exception {
		assertThat(compraRepository).isInstanceOf(CompraRepository.class);
	}

    @Test
	public void fornecedorRepositoryIsInstance() throws Exception {
		assertThat(fornecedorRepository).isInstanceOf(FornecedorRepository.class);
	}
    
    @Test
	public void clienteRepositoryIsInstance() throws Exception {
		assertThat(clienteRepository).isInstanceOf(ClienteRepository.class);
	}
    
    @Test
	public void funcionarioRepositoryIsInstance() throws Exception {
		assertThat(funcionarioRepository).isInstanceOf(FuncionarioRepository.class);
	}
    
    @Test
	public void marcaRepositoryIsInstance() throws Exception {
		assertThat(marcaRepository).isInstanceOf(MarcaRepository.class);
	}
    
    @Test
	public void telefoneRepositoryIsInstance() throws Exception {
		assertThat(telefoneRepository).isInstanceOf(TelefoneRepository.class);
	}

    @Test
	public void produtoRepositoryIsInstance() throws Exception {
		assertThat(produtoRepository).isInstanceOf(ProdutoRepository.class);
	}
    
}
