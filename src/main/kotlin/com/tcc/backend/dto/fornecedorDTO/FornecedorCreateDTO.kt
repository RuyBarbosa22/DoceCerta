package com.tcc.backend.dto.fornecedorDTO

import com.tcc.backend.dto.enderecoDTO.EnderecoDTO
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class FornecedorCreateDTO(
    @field:NotBlank(message = "Nome do fornecedor é obrigatório")
    val nome: String,

    val endereco: EnderecoDTO?,  // Pode ser opcional, mas você pode ajustar para obrigatório se quiser

    @field:Email(message = "Email inválido")
    val email: String? = null,

    @field:NotBlank(message = "CNPJ é obrigatório")
    @field:Size(min = 14, max = 18, message = "CNPJ deve ter tamanho válido")
    val cnpj: String
)
