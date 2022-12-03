package br.com.electronicsforward;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.electronicsforward.service.ClienteService;
import br.com.electronicsforward.service.CompraService;
import br.com.electronicsforward.service.FornecedorService;
import br.com.electronicsforward.service.FuncionarioService;
import br.com.electronicsforward.service.MarcaService;
import br.com.electronicsforward.service.ProdutoService;
import br.com.electronicsforward.service.TelefoneService;
import br.com.electronicsforward.service.TipoPagamentoService;
import br.com.electronicsforward.service.VendaService;

@SpringBootTest
public class ServiceTest {
    
    @Autowired
	private VendaService vendaService;

    @Autowired
	private TipoPagamentoService tipoPagamentoService;

    @Autowired
	private CompraService compraService;

    @Autowired
	private FornecedorService fornecedorService;

    @Autowired
	private ClienteService clienteService;

    @Autowired
	private FuncionarioService funcionarioService;

    @Autowired
	private MarcaService marcaService;

    @Autowired
	private TelefoneService telefoneService;

    @Autowired
	private ProdutoService produtoService;

	@Test
	public void vendaServiceNotNull() throws Exception {
		assertThat(vendaService).isNotNull();
	}

    @Test
	public void tipoPagamentoServiceNotNull() throws Exception {
		assertThat(tipoPagamentoService).isNotNull();
	}

    @Test
	public void compraServiceNotNull() throws Exception {
		assertThat(compraService).isNotNull();
	}

    @Test
	public void fornecedorServiceNotNull() throws Exception {
		assertThat(fornecedorService).isNotNull();
	}
    
    @Test
	public void clienteServiceNotNull() throws Exception {
		assertThat(clienteService).isNotNull();
	}
    
    @Test
	public void funcionarioServiceNotNull() throws Exception {
		assertThat(funcionarioService).isNotNull();
	}
    
    @Test
	public void marcaServiceNotNull() throws Exception {
		assertThat(marcaService).isNotNull();
	}
    
    @Test
	public void telefoneServiceNotNull() throws Exception {
		assertThat(telefoneService).isNotNull();
	}
    
    @Test
	public void produtoServiceNotNull() throws Exception {
		assertThat(produtoService).isNotNull();
	}

    @Test
	public void vendaServiceIsInstance() throws Exception {
		assertThat(vendaService).isInstanceOf(VendaService.class);
	}

    @Test
	public void tipoPagamentoServiceIsInstance() throws Exception {
		assertThat(tipoPagamentoService).isInstanceOf(TipoPagamentoService.class);
	}

    @Test
	public void compraServiceIsInstance() throws Exception {
		assertThat(compraService).isInstanceOf(CompraService.class);
	}

    @Test
	public void fornecedorServiceIsInstance() throws Exception {
		assertThat(fornecedorService).isInstanceOf(FornecedorService.class);
	}
    
    @Test
	public void clienteServiceIsInstance() throws Exception {
		assertThat(clienteService).isInstanceOf(ClienteService.class);
	}
    
    @Test
	public void funcionarioServiceIsInstance() throws Exception {
		assertThat(funcionarioService).isInstanceOf(FuncionarioService.class);
	}
    
    @Test
	public void marcaServiceIsInstance() throws Exception {
		assertThat(marcaService).isInstanceOf(MarcaService.class);
	}
    
    @Test
	public void telefoneServiceIsInstance() throws Exception {
		assertThat(telefoneService).isInstanceOf(TelefoneService.class);
	}

    @Test
	public void produtoServiceIsInstance() throws Exception {
		assertThat(produtoService).isInstanceOf(ProdutoService.class);
	}

}
