package com.tcc.backend.repository

import com.tcc.backend.models.PedidoReestoque
import com.tcc.backend.models.enums.StatusPedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoReestoqueRepository : JpaRepository<PedidoReestoque, Int> {
    fun findByStatus(status: StatusPedido): List<PedidoReestoque>
    fun findByFilialSolicitanteId(filialId: Int): List<PedidoReestoque>
}