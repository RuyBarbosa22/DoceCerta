package com.tcc.backend.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.*

data class EmpresaDTO(

    @field:NotBlank(message = "O nome da empresa é obrigatório")
    @field:Size(max = 100, message = "O nome da empresa deve ter no máximo 100 caracteres")
    val nome: String,

    @field:NotBlank(message = "O CNPJ é obrigatório")
    @field:Pattern(
        regexp = "\\d{14}",
        message = "O CNPJ deve conter exatamente 14 dígitos numéricos"
    )
    val cnpj: String,

    @field:Size(max = 240, message = "A descrição deve ter no máximo 240 caracteres")
    val descricao: String? = null,

    @field:Email(message = "O e-mail informado é inválido")
    val email: String? = null,

    @field:NotNull(message = "O endereço da empresa é obrigatório")
    @field:Valid
    val endereco: EnderecoDTO
)
