package com.tcc.backend.mappers

import com.tcc.backend.dto.EstoqueFilialResponseDTO
import com.tcc.backend.models.EstoqueFilial

fun EstoqueFilial.toResponseDTO(): EstoqueFilialResponseDTO {
    return EstoqueFilialResponseDTO(
        produto = this.produto?.toSimplesResponseDTO(),
        filial = this.filial?.toSimplesResponseDTO(),
        quantidadeAtual = this.quantidadeAtual,
        estoqueMinimo = this.estoqueMinimo
    )
}