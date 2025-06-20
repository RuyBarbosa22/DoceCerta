package com.tcc.backend.dto

data class FornecedorResponseDTO(
    /** O identificador único do fornecedor. */
    val id: Int,

    /** O nome do fornecedor. */
    val nome: String?,

    /** O CNPJ do fornecedor. */
    val cnpj: String?,

    /** O email de contato do fornecedor. */
    val email: String?,

    /** O endereço associado ao fornecedor, se houver. */
    val endereco: EnderecoDTO?
)