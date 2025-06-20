package com.tcc.backend.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AtualizarContatoDTO(
    @field:NotBlank(message = "O número de contato é obrigatório")
    @field:Pattern(
        regexp = "\\d{10,11}",
        message = "O número de contato deve conter entre 10 e 11 dígitos numéricos"
    )
    val numeroCtt: String
)
