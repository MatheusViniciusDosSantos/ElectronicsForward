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
    @Schema(description = "Data de Cadastro do Tipo de Pagamento. Gerado na criação de um novo Tipo de Pagamento")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Data de Atualização do Tipo de Pagamento. Gerado na alteração de um novo Tipo de Pagamento")
    private Date dataUltimaAlteracao;

}
