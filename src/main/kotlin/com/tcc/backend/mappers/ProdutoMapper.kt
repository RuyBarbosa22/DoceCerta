package com.tcc.backend.mappers

import com.tcc.backend.dto.ProdutoResponseDTO
import com.tcc.backend.dto.ProdutoSimplesResponseDTO
import com.tcc.backend.dto.toSimplesResponseDTO
import com.tcc.backend.models.Produto

fun Produto.toResponseDTO(): ProdutoResponseDTO {
    return ProdutoResponseDTO(
        id = this.id,
        nome = this.nome,
        descricao = this.descricao,
        preco = this.preco,
        custo = this.custo,
        validade = this.validade,
        categoria = this.categoria,
        quantidade = this.quantidade,
        fornecedor = this.fornecedor?.toSimplesResponseDTO()
    )
}

fun Produto.toSimplesResponseDTO(): ProdutoSimplesResponseDTO {
    return ProdutoSimplesResponseDTO(
        id = this.id,
        nome = this.nome
    )
}