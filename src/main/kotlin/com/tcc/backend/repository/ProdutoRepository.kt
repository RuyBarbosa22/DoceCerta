package com.tcc.backend.repository

import com.tcc.backend.models.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : JpaRepository<Produto, Int> {

    fun findByCategoria(categoria: String): List<Produto>

    fun findByFornecedorId(fornecedorId: Int): List<Produto>

    fun findByFornecedorNome(nome: String): List<Produto>

    fun findAllByFornecedorNome(nomeFornecedor: String): List<Produto>

    fun findByNome(nome: String): Produto?

}
