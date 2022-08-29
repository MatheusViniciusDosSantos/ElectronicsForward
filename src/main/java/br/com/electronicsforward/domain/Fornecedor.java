package br.com.electronicsforward.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fornecedor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	public Fornecedor() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;
	
	private String contato;
	
	private String cnpj;
	
	private String endereco;
	
	private char status;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Cadastro do fornececor. Gerado na criação de um novo fornececor")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Atualização do fornececor. Gerado na alteração de um novo fornececor")
	private Date dataUltimaAlteracao;

	
}
