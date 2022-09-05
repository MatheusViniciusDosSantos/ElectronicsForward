package br.com.electronicsforward.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "compra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Compra {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Double desconto;

    @OneToOne
    @JoinColumn(name = "idFornecedor")
    private Fornecedor fornecedor;

    @OneToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;

    @OneToOne
    @JoinColumn(name = "idTipoPagamento")
    private TipoPagamento tipoPagamento;

    private char status;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Cadastro da compra. Gerado na criação de uma nova compra")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Atualização da compra. Gerado na alteração de uma nova compra")
    private Date dataUltimaAlteracao;

    public Compra() {}

}
