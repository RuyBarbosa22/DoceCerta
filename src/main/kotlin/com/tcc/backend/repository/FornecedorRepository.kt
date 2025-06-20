package com.tcc.backend.repository

import com.tcc.backend.models.Fornecedor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FornecedorRepository : JpaRepository<Fornecedor, Int> {
    fun findByNome(nome: String): Fornecedor?
}
