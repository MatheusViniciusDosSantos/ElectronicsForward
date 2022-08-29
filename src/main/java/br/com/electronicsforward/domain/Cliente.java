package br.com.electronicsforward.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	public Cliente() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;

	private String cpf;

	private char status;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Cadastro do cliente. Gerado na criação de um novo cliente")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Atualização do cliente. Gerado na alteração de um novo cliente")
	private Date dataUltimaAlteracao;


}
