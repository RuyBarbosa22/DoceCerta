    package com.tcc.backend.repository

    import com.tcc.backend.models.Cliente
    import org.springframework.data.jpa.repository.JpaRepository

    interface ClienteRepository : JpaRepository<Cliente, Int> {

        fun findByCpf(cpf: String): Cliente?

        fun deleteByCpf(cpf: String): Long
    }
