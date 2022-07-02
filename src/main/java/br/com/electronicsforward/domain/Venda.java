package br.com.electronicsforward.domain;

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

    private Date dataCadastro;

    public Venda() {
        this.dataCadastro = Calendar.getInstance().getTime();
    }

}
