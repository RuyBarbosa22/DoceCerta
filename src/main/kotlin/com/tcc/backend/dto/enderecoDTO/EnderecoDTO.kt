package com.tcc.backend.dto.enderecoDTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class EnderecoDTO(
    @field:NotBlank(message = "O nome da rua é obrigatório")
    val rua: String,
    @field:NotBlank(message = "O número do endereço é obrigatório")
    val numero: String,
    @field:NotBlank(message = "O bairro é obrigatório")
    val bairro: String,
    @field:NotBlank(message = "A cidade é obrigatória")
    val cidade: String,
    @field:NotBlank(message = "O estado é obrigatório")
    @field:Pattern(regexp = "[A-Z]{2}", message = "O estado deve conter exatamente 2 letras maiúsculas (ex: SP, RJ)")
    val estado: String,
    @field:NotBlank(message = "O CEP é obrigatório")
    @field:Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos")
    val cep: String
)