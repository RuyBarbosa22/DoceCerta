package com.tcc.backend.dto

data class EmpresaResponseDTO(
    val id: Int,
    val nome: String?,
    val email: String?,
    val descricao: String?,
    val cnpj: String?,
    val endereco: EnderecoDTO?,
    val filiais: List<FilialSimplesResponseDTO>
)