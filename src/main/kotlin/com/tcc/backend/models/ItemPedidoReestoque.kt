package com.tcc.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "itens_pedido_reestoque")
class ItemPedidoReestoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    var produto: Produto? = null

    @Column(name = "quantidade_solicitada", nullable = false)
    var quantidadeSolicitada: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_reestoque_id", nullable = false)
    var pedidoReestoque: PedidoReestoque? = null
}