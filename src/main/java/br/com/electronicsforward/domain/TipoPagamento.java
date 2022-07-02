package br.com.electronicsforward.domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "tipo_pagamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class TipoPagamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String tipo;

}
