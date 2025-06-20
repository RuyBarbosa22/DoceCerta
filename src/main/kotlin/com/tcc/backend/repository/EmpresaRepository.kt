package com.tcc.backend.repository

import com.tcc.backend.models.Empresa
import org.springframework.data.jpa.repository.JpaRepository

interface EmpresaRepository : JpaRepository<Empresa, Int> {

    fun findByCnpj(cnpj: String): Empresa?

    fun deleteByCnpj(cnpj: String): Long
}
