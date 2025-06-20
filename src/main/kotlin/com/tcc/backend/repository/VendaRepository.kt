package com.tcc.backend.repository

import com.tcc.backend.models.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface VendaRepository : JpaRepository<Venda, Int> {

    /**
     * Filtra as vendas no banco de dados com base em múltiplos critérios opcionais.
     * @param dataInicio A busca considerará vendas a partir da meia-noite deste dia.
     * @param dataFim A busca considerará vendas até o final deste dia (23:59:59).
     */
    @Query("""
        SELECT v FROM Venda v
        LEFT JOIN v.cliente c
        LEFT JOIN v.produto p
        LEFT JOIN p.fornecedor f
        WHERE 
            (:cpfCliente IS NULL OR c.cpf = :cpfCliente) AND
            (:dataInicio IS NULL OR v.dataVenda >= :dataInicio) AND
            (:dataFim IS NULL OR v.dataVenda <= :dataFim) AND
            (:formaPagamento IS NULL OR v.formaPagamento = :formaPagamento) AND
            (:unidade IS NULL OR v.unidade = :unidade) AND
            (:horario IS NULL OR FUNCTION('TIME_FORMAT', v.dataVenda, '%H:%i:%s') = :horario) AND
            (:categoriaProduto IS NULL OR p.categoria = :categoriaProduto) AND
            (:fornecedorProduto IS NULL OR f.nome = :fornecedorProduto)
    """)
    fun filtrarVendas(
        @Param("cpfCliente") cpfCliente: String?,
        @Param("dataInicio") dataInicio: LocalDateTime?,
        @Param("dataFim") dataFim: LocalDateTime?,
        @Param("formaPagamento") formaPagamento: String?,
        @Param("unidade") unidade: String?,
        @Param("horario") horario: String?,
        @Param("categoriaProduto") categoriaProduto: String?,
        @Param("fornecedorProduto") fornecedorProduto: String?
    ): List<Venda>
}