package com.tcc.backend.dto.filialDTO

import com.tcc.backend.dto.enderecoDTO.EnderecoDTO

data class FilialResponseDTO(
    val id: Int,
    val nome: String?,
    val descricao: String?,
    val numero: String?,
    val matriz: Boolean,
    val endereco: EnderecoDTO?
)