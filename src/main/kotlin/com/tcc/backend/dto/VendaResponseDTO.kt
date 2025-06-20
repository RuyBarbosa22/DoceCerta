package com.tcc.backend.dto

import java.time.LocalDateTime

data class VendaResponseDTO(
    val id: Int,
    val dataVenda: LocalDateTime?,
    val precoUnitario: Double?,
    val valorTotal: Double?,
    val formaPagamento: String?,
    val desconto: Double?,
    val quantidade: Int?,
    val unidade: String?,
    val produto: ProdutoSimplesResponseDTO?,
    val cliente: ClienteSimplesResponseDTO?
)