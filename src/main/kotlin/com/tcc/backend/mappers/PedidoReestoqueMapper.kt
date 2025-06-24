package com.tcc.backend.mappers

import com.tcc.backend.dto.PedidoReestoqueResponseDTO
import com.tcc.backend.models.PedidoReestoque

fun PedidoReestoque.toResponseDTO(): PedidoReestoqueResponseDTO {
    return PedidoReestoqueResponseDTO(
        id = this.id,
        dataPedido = this.dataPedido,
        status = this.status,
        observacoes = this.observacoes,
        filialSolicitante = this.filialSolicitante?.toSimplesResponseDTO(),
        itens = this.itens.map { it.toResponseDTO() } // Mapeia cada item da lista
    )
}