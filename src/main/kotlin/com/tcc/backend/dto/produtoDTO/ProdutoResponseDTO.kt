package com.tcc.backend.dto.produtoDTO

import com.tcc.backend.dto.fornecedorDTO.FornecedorSimplesResponseDTO
import com.tcc.backend.models.Fornecedor
import java.time.LocalDate

data class ProdutoResponseDTO(
    val id: Int,
    val nome: String?,
    val descricao: String?,
    val preco: Double?,
    val custo: Double?,
    val validade: LocalDate?,
    val categoria: String?,
    val quantidade: Int?,
    val fornecedor: FornecedorSimplesResponseDTO? // Usando o DTO simples
)

fun Fornecedor.toSimplesResponseDTO(): FornecedorSimplesResponseDTO {
    return FornecedorSimplesResponseDTO(
        id = this.id,
        nome = this.nome
    )
}