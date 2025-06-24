package com.tcc.backend.mappers

import com.tcc.backend.dto.enderecoDTO.EnderecoDTO
import com.tcc.backend.models.Endereco

fun Endereco.toDTO(): EnderecoDTO {
    return EnderecoDTO(
        rua = this.rua!!,
        numero = this.numero!!,
        bairro = this.bairro!!,
        cidade = this.cidade!!,
        estado = this.estado!!,
        cep = this.cep!!
    )
}

fun EnderecoDTO.toEntity(): Endereco {
    return Endereco().apply {
        rua = this@toEntity.rua
        numero = this@toEntity.numero
        bairro = this@toEntity.bairro
        cidade = this@toEntity.cidade
        estado = this@toEntity.estado
        cep = this@toEntity.cep
    }
}