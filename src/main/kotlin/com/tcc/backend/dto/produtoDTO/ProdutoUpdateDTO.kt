package com.tcc.backend.dto.produtoDTO

import jakarta.validation.constraints.*
import java.time.LocalDate

data class ProdutoUpdateDTO(

    @field:Size(max = 100, message = "O nome do produto pode ter no máximo 100 caracteres")
    val nome: String?,

    @field:Size(max = 240, message = "A descrição pode ter no máximo 240 caracteres")
    val descricao: String?,

    @field:Positive(message = "O preço deve ser maior que zero")
    val preco: Double?,

    @field:PositiveOrZero(message = "O custo não pode ser negativo")
    val custo: Double?,

    @field:FutureOrPresent(message = "A validade não pode ser no passado")
    val validade: LocalDate?,

    @field:Size(max = 60, message = "A categoria pode ter no máximo 60 caracteres")
    val categoria: String?,

    @field:Min(value = 0, message = "A quantidade não pode ser negativa")
    val quantidade: Int?,

    @field:Size(max = 100, message = "O nome do fornecedor pode ter no máximo 100 caracteres")
    val nomeFornecedor: String?
)