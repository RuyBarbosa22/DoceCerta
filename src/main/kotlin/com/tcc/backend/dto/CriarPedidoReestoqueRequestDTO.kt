package com.tcc.backend.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CriarPedidoReestoqueRequestDTO(
    @field:Size(max = 255, message = "As observações devem ter no máximo 255 caracteres")
    val observacoes: String?,

    @field:NotEmpty(message = "O pedido deve conter pelo menos um item")
    @field:Valid // Garante que os itens da lista também sejam validados
    val itens: List<ItemPedidoRequestDTO>
)