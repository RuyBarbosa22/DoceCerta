package com.tcc.backend.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class AtualizarEstoqueRequestDTO(
    @field:NotNull(message = "A quantidade atual é obrigatória")
    @field:Min(value = 0, message = "A quantidade não pode ser negativa")
    val quantidadeAtual: Int,

    @field:NotNull(message = "O estoque mínimo é obrigatório")
    @field:Min(value = 0, message = "O estoque mínimo não pode ser negativo")
    val estoqueMinimo: Int
)