package com.tcc.backend.dto

import com.tcc.backend.dto.produtoDTO.ProdutoSimplesResponseDTO

data class ItemPedidoResponseDTO(
    val id: Int,
    val quantidadeSolicitada: Int,
    val produto: ProdutoSimplesResponseDTO?
)