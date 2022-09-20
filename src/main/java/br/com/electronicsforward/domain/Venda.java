package br.com.electronicsforward.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "venda")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Venda {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Double desconto;

    private Double valorFinal;

    @OneToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "idTipoPagamento")
    private TipoPagamento tipoPagamento;

    private char status;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Cadastro da venda. Gerado na criação de uma nova venda")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Atualização da venda. Gerado na alteração de uma nova venda")
    private Date dataUltimaAlteracao;

    public Venda() {
        this.dataCadastro = Calendar.getInstance().getTime();
    }

}
