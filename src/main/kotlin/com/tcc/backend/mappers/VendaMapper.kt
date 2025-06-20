package com.tcc.backend.mappers

import com.tcc.backend.dto.VendaResponseDTO
import com.tcc.backend.models.Venda

fun Venda.toResponseDTO(): VendaResponseDTO {
    return VendaResponseDTO(
        id = this.id,
        dataVenda = this.dataVenda,
        precoUnitario = this.precoUnitario,
        valorTotal = this.valorTotal,
        formaPagamento = this.formaPagamento,
        desconto = this.desconto,
        quantidade = this.quantidade,
        unidade = this.unidade,
        produto = this.produto?.toSimplesResponseDTO(),
        cliente = this.cliente?.toSimplesResponseDTO()
    )
}