package com.tcc.backend.mappers

import com.tcc.backend.dto.filialDTO.FilialResponseDTO
import com.tcc.backend.dto.filialDTO.FilialSimplesResponseDTO
import com.tcc.backend.models.Filial

// Esta função você já deve ter
fun Filial.toSimplesResponseDTO(): FilialSimplesResponseDTO {
    return FilialSimplesResponseDTO(
        id = this.id,
        nome = this.nome
    )
}

fun Filial.toResponseDTO(): FilialResponseDTO {
    return FilialResponseDTO(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        numero = this.numero,
        matriz = this.matriz,
        endereco = this.endereco?.toDTO()
    )
}