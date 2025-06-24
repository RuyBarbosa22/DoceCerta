package com.tcc.backend.mappers

import com.tcc.backend.dto.ItemPedidoResponseDTO
import com.tcc.backend.models.ItemPedidoReestoque

fun ItemPedidoReestoque.toResponseDTO(): ItemPedidoResponseDTO {
    return ItemPedidoResponseDTO(
        id = this.id,
        quantidadeSolicitada = this.quantidadeSolicitada,
        produto = this.produto?.toSimplesResponseDTO()
    )
}