package com.tcc.backend.dto

data class FilialResponseDTO(
    val id: Int,
    val nome: String?,
    val descricao: String?,
    val numero: String?,
    val matriz: Boolean,
    val endereco: EnderecoDTO?
)