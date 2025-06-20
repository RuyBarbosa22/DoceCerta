package com.tcc.backend.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class FornecedorUpdateDTO(
    val nome: String?,

    val endereco: EnderecoDTO?,

    @field:Email(message = "Email inválido")
    val email: String?,

    @field:Size(min = 14, max = 18, message = "CNPJ deve ter tamanho válido")
    val cnpj: String?  // Se quiser permitir alteração do CNPJ, senão pode remover daqui
)
