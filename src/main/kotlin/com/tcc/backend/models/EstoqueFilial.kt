package com.tcc.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "estoque_filial")
@IdClass(EstoqueFilialId::class)
class EstoqueFilial {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    var produto: Produto? = null

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filial_id")
    var filial: Filial? = null

    @Column(name = "quantidade_atual", nullable = false)
    var quantidadeAtual: Int = 0

    @Column(name = "estoque_minimo", nullable = false)
    var estoqueMinimo: Int = 0
}