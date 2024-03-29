package br.com.electronicsforward.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "funcionario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	public Funcionario() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;

	private String cpf;

	private char status;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Cadastro do Funcionário. Gerado na criação de um nova funcionário")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Atualização do Funcionário. Gerado na alteração de um novo funcionário")
	private Date dataUltimaAlteracao;


	
}
