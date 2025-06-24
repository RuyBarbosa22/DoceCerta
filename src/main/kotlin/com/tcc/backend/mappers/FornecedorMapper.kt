package com.tcc.backend.mappers

import com.tcc.backend.dto.fornecedorDTO.FornecedorResponseDTO
import com.tcc.backend.models.Fornecedor

fun Fornecedor.toResponseDTO(): FornecedorResponseDTO {
    return FornecedorResponseDTO(
        id = this.id,
        nome = this.nome,
        cnpj = this.cnpj,
        email = this.email,
        endereco = this.endereco?.toDTO() // Reutiliza o mapper de endereço
    )
}