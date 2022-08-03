package br.com.electronicsforward.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tipo_pagamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String tipo;

    @Schema(description = "Status do Tipo de Pagamento", example = "A")
    private char status;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Cadastro da marca. Gerado na criação de uma nova marca")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Atualização da marca. Gerado na alteração de uma nova marca")
    private Date dataUltimaAlteracao;

}
