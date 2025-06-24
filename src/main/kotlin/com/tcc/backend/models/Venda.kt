package com.tcc.backend.models

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "venda")
class Venda {

    @Column(name = "id", unique = true)
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "data_venda")
    var dataVenda: LocalDateTime? = null

    @Column(name = "preco_unitario")
    var precoUnitario: Double? = null

    @Column(name = "valor_total")
    var valorTotal: Double? = null

    @Column(name = "forma_pagamento")
    var formaPagamento: String? = null

    @Column(name = "desconto")
    var desconto: Double? = null

    @Column(name = "quantidade")
    var quantidade: Int? = null

    @Column(name = "unidade")
    var unidade: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_produto", referencedColumnName = "id", nullable = false)
    var produto: Produto? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fk_cliente", referencedColumnName = "id")
    var cliente: Cliente? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_filial", referencedColumnName = "id", nullable = false)
    var filial: Filial? = null
}
