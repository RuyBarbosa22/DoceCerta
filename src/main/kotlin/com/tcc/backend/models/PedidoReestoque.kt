package com.tcc.backend.models

import com.tcc.backend.models.enums.StatusPedido
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "pedidos_reestoque")
class PedidoReestoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "data_pedido", nullable = false)
    var dataPedido: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: StatusPedido = StatusPedido.PENDENTE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filial_solicitante_id", nullable = false)
    var filialSolicitante: Filial? = null

    @Column(name = "observacoes")
    var observacoes: String? = null

    @OneToMany(mappedBy = "pedidoReestoque", cascade = [CascadeType.ALL], orphanRemoval = true)
    var itens: MutableList<ItemPedidoReestoque> = mutableListOf()
}