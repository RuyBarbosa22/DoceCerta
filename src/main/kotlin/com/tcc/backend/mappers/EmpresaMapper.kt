package com.tcc.backend.mappers

import com.tcc.backend.dto.EmpresaResponseDTO
import com.tcc.backend.models.Empresa

fun Empresa.toResponseDTO(): EmpresaResponseDTO {
    return EmpresaResponseDTO(
        id = this.id,
        nome = this.nome,
        email = this.email,
        descricao = this.descricao,
        cnpj = this.cnpj,
        endereco = this.endereco?.toDTO(), // Usa o EnderecoMapper que já temos
        filiais = this.filiais.map { it.toSimplesResponseDTO() } // Usa o FilialMapper para cada item da lista
    )
}