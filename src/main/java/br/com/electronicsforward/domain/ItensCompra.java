package br.com.electronicsforward.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name = "itens_compra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class ItensCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Schema(description = "Valor de custo do produto", example = "3.00")
	private Double valorCusto;
	
	@Schema(description = "Quantidade de produtos")
	private int quantidade;

	@Schema(description = "Produto")
	@ManyToOne
	@JoinColumn(name = "idProduto")
	@NotBlank
	private Produto produto;

	@Schema(description = "Compra dos produtos")
	@ManyToOne
	@JoinColumn(name = "idCompra")
	@NotBlank
	private Compra compra;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Cadastro dos itens da compra. Gerado na criação de uma nova compra")
	private Date dataCadastro;
}
