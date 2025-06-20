package com.tcc.backend.mappers

import com.tcc.backend.dto.ClienteResponseDTO
import com.tcc.backend.dto.ClienteSimplesResponseDTO
import com.tcc.backend.models.Cliente

fun Cliente.toResponseDTO(): ClienteResponseDTO {
    return ClienteResponseDTO(
        id = this.id,
        nome = this.nome,
        cpf = this.cpf,
        numeroCtt = this.numeroCtt,
        dataNascimento = this.dataNascimento,
        sexo = this.sexo,
        dataCadastro = this.dataCadastro,
        endereco = this.endereco?.toDTO()
    )
}

fun Cliente.toSimplesResponseDTO(): ClienteSimplesResponseDTO {
    return ClienteSimplesResponseDTO(
        id = this.id,
        nome = this.nome,
        cpf = this.cpf
    )
}