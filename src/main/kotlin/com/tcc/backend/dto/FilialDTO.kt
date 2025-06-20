package com.tcc.backend.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.*

data class FilialDTO(

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(max = 100, message = "Nome pode ter no máximo 100 caracteres")
    val nome: String,

    @field:Size(max = 240, message = "Descrição pode ter no máximo 240 caracteres")
    val descricao: String? = null,

    @field:NotBlank(message = "Número é obrigatório")
    @field:Size(max = 20, message = "Número pode ter no máximo 20 caracteres")
    val numero: String,

    val matriz: Boolean = false,

    @field:Valid
    @field:NotNull(message = "Endereço é obrigatório")
    val endereco: EnderecoDTO
)
