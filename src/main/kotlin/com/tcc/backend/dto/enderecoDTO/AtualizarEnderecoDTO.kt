package com.tcc.backend.dto.enderecoDTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AtualizarEnderecoDTO(
    @field:NotBlank(message = "O CEP é obrigatório")
    @field:Pattern(
        regexp = "\\d{8}",
        message = "O CEP deve conter exatamente 8 dígitos numéricos"
    )
    val cep: String,

    @field:NotBlank(message = "O nome da rua é obrigatório")
    @field:Size(max = 100, message = "A rua deve ter no máximo 100 caracteres")
    val rua: String,

    @field:NotBlank(message = "O número é obrigatório")
    @field:Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    val numero: String
)