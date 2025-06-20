package com.tcc.backend.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CriarClienteRequestDTO(

    @field:NotBlank(message = "O nome do cliente é obrigatório")
    @field:Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    val nome: String,

    @field:NotBlank(message = "O CPF do cliente é obrigatório")
    @field:Pattern(
        regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
        message = "O CPF deve seguir o formato XXX.XXX.XXX-XX"
    )
    val cpf: String,

    @field:NotBlank(message = "O número de contato é obrigatório")
    @field:Size(min = 10, max = 15, message = "O número de contato deve ter entre 10 e 15 caracteres")
    val numeroCtt: String,

    @field:NotNull(message = "A data de nascimento é obrigatória")
    @field:Past(message = "A data de nascimento deve ser uma data no passado")
    val dataNascimento: LocalDate,

    @field:NotBlank(message = "O sexo é obrigatório")
    val sexo: String,

    @field:NotNull(message = "O endereço é obrigatório")
    @field:Valid // ESSENCIAL: Garante que as validações dentro de EnderecoDTO sejam ativadas
    val endereco: EnderecoDTO
)