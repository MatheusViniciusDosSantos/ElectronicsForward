package br.com.electronicsforward.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Entity
@Table(name = "telefone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Telefone {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
	
	@NotBlank
	private String numero;

    @ManyToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    private char status;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de cadastro do telefone. Gerado no cadastro de um telefone")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "Data de Atualização do telefone. Gerado na alteração de um telefone")
	private Date dataUltimaAlteracao;

    public Telefone() {
        this.dataCadastro = Calendar.getInstance().getTime();
    }
}
