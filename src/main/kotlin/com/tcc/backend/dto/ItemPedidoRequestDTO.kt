package com.tcc.backend.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class ItemPedidoRequestDTO(
    @field:NotNull(message = "O ID do produto é obrigatório")
    val produtoId: Int,

    @field:NotNull(message = "A quantidade solicitada é obrigatória")
    @field:Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    val quantidadeSolicitada: Int
)