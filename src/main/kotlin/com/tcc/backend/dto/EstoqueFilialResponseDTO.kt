package com.tcc.backend.dto

import com.tcc.backend.dto.filialDTO.FilialSimplesResponseDTO
import com.tcc.backend.dto.produtoDTO.ProdutoSimplesResponseDTO

data class EstoqueFilialResponseDTO(
    val produto: ProdutoSimplesResponseDTO?,
    val filial: FilialSimplesResponseDTO?,
    val quantidadeAtual: Int,
    val estoqueMinimo: Int
)