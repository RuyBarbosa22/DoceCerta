package com.tcc.backend.dto

import com.tcc.backend.dto.filialDTO.FilialSimplesResponseDTO
import com.tcc.backend.models.enums.StatusPedido
import java.time.LocalDateTime

data class PedidoReestoqueResponseDTO(
    val id: Int,
    val dataPedido: LocalDateTime,
    val status: StatusPedido,
    val observacoes: String?,
    val filialSolicitante: FilialSimplesResponseDTO?,
    val itens: List<ItemPedidoResponseDTO>
)