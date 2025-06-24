package com.tcc.backend.dto.produtoDTO

import jakarta.validation.constraints.*
import java.time.LocalDate

data class ProdutoDTO(

    @field:NotBlank(message = "O nome do produto é obrigatório")
    @field:Size(max = 100, message = "O nome do produto pode ter no máximo 100 caracteres")
    val nome: String,

    @field:Size(max = 240, message = "A descrição pode ter no máximo 240 caracteres")
    val descricao: String? = null,

    @field:NotNull(message = "O preço é obrigatório")
    @field:Positive(message = "O preço deve ser maior que zero")
    val preco: Double,

    @field:NotNull(message = "O custo é obrigatório")
    @field:PositiveOrZero(message = "O custo não pode ser negativo")
    val custo: Double,

    @field:FutureOrPresent(message = "A validade não pode ser no passado")
    val validade: LocalDate? = null,

    @field:NotBlank(message = "A categoria é obrigatória")
    @field:Size(max = 60, message = "A categoria pode ter no máximo 60 caracteres")
    val categoria: String,

    @field:NotNull(message = "A quantidade é obrigatória")
    @field:Min(value = 0, message = "A quantidade não pode ser negativa")
    val quantidade: Int,

    @field:NotBlank(message = "O nome do fornecedor é obrigatório")
    @field:Size(max = 100, message = "O nome do fornecedor pode ter no máximo 100 caracteres")
    val nomeFornecedor: String
)
