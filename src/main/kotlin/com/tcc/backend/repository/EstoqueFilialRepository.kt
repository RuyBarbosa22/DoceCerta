package com.tcc.backend.repository

import com.tcc.backend.models.EstoqueFilial
import com.tcc.backend.models.EstoqueFilialId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EstoqueFilialRepository : JpaRepository<EstoqueFilial, EstoqueFilialId> {
    fun findByProdutoIdAndFilialId(produtoId: Int, filialId: Int): EstoqueFilial?
}