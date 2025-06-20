package com.tcc.backend.repository

import com.tcc.backend.models.Filial
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilialRepository : JpaRepository<Filial, Int> {
    fun findByEmpresaCnpjAndNome(cnpj: String, nome: String): Filial?
}
