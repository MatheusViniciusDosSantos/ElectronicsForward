package br.com.electronicsforward;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.electronicsforward.controller.ClienteController;
import br.com.electronicsforward.controller.CompraController;
import br.com.electronicsforward.controller.FornecedorController;
import br.com.electronicsforward.controller.FuncionarioController;
import br.com.electronicsforward.controller.MarcaController;
import br.com.electronicsforward.controller.ProdutoController;
import br.com.electronicsforward.controller.TelefoneController;
import br.com.electronicsforward.controller.TipoPagamentoController;
import br.com.electronicsforward.controller.VendaController;

@SpringBootTest
public class ControllerTest {
    @Autowired
	private VendaController vendaController;

    @Autowired
	private TipoPagamentoController tipoPagamentoController;

    @Autowired
	private CompraController compraController;

    @Autowired
	private FornecedorController fornecedorController;

    @Autowired
	private ClienteController clienteController;

    @Autowired
	private FuncionarioController funcionarioController;

    @Autowired
	private MarcaController marcaController;

    @Autowired
	private TelefoneController telefoneController;

    @Autowired
	private ProdutoController produtoController;

	@Test
	public void vendaControllerNotNull() throws Exception {
		assertThat(vendaController).isNotNull();
	}

    @Test
	public void tipoPagamentoControllerNotNull() throws Exception {
		assertThat(tipoPagamentoController).isNotNull();
	}

    @Test
	public void compraControllerNotNull() throws Exception {
		assertThat(compraController).isNotNull();
	}

    @Test
	public void fornecedorControllerNotNull() throws Exception {
		assertThat(fornecedorController).isNotNull();
	}
    
    @Test
	public void clienteControllerNotNull() throws Exception {
		assertThat(clienteController).isNotNull();
	}
    
    @Test
	public void funcionarioControllerNotNull() throws Exception {
		assertThat(funcionarioController).isNotNull();
	}
    
    @Test
	public void marcaControllerNotNull() throws Exception {
		assertThat(marcaController).isNotNull();
	}
    
    @Test
	public void telefoneControllerNotNull() throws Exception {
		assertThat(telefoneController).isNotNull();
	}
    
    @Test
	public void produtoControllerNotNull() throws Exception {
		assertThat(produtoController).isNotNull();
	}

    @Test
	public void vendaControllerIsInstance() throws Exception {
		assertThat(vendaController).isInstanceOf(VendaController.class);
	}

    @Test
	public void tipoPagamentoControllerIsInstance() throws Exception {
		assertThat(tipoPagamentoController).isInstanceOf(TipoPagamentoController.class);
	}

    @Test
	public void compraControllerIsInstance() throws Exception {
		assertThat(compraController).isInstanceOf(CompraController.class);
	}

    @Test
	public void fornecedorControllerIsInstance() throws Exception {
		assertThat(fornecedorController).isInstanceOf(FornecedorController.class);
	}
    
    @Test
	public void clienteControllerIsInstance() throws Exception {
		assertThat(clienteController).isInstanceOf(ClienteController.class);
	}
    
    @Test
	public void funcionarioControllerIsInstance() throws Exception {
		assertThat(funcionarioController).isInstanceOf(FuncionarioController.class);
	}
    
    @Test
	public void marcaControllerIsInstance() throws Exception {
		assertThat(marcaController).isInstanceOf(MarcaController.class);
	}
    
    @Test
	public void telefoneControllerIsInstance() throws Exception {
		assertThat(telefoneController).isInstanceOf(TelefoneController.class);
	}

    @Test
	public void produtoControllerIsInstance() throws Exception {
		assertThat(produtoController).isInstanceOf(ProdutoController.class);
	}
    
}
