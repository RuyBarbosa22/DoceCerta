package com.tcc.backend.service

import com.tcc.backend.dto.AtualizarEstoqueRequestDTO
import com.tcc.backend.models.EstoqueFilial
import com.tcc.backend.models.Venda
import com.tcc.backend.repository.EstoqueFilialRepository
import com.tcc.backend.repository.FilialRepository
import com.tcc.backend.repository.ProdutoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EstoqueService(
    private val estoqueRepository: EstoqueFilialRepository,
    private val produtoRepository: ProdutoRepository,
    private val filialRepository: FilialRepository
) {

    @Transactional
    fun darBaixaEstoque(venda: Venda) {
        val produto = venda.produto ?: throw IllegalStateException("Venda sem produto associado.")
        // Assumindo que a venda ocorre em uma filial. Precisamos dessa informação na Venda.
        // Por agora, vamos assumir que existe uma filial padrão ou que a informação virá de algum lugar.
        // Esta parte precisará de ajuste quando definirmos a filial da venda.
        // Exemplo: val filialId = venda.caixa.filial.id
        // val filialId = 1 // Placeholder

        // val estoque = estoqueRepository.findByProdutoIdAndFilialId(produto.id, filialId)
        //     ?: throw IllegalStateException("Estoque não encontrado para o produto ${produto.nome} na filial $filialId.")

        // if (estoque.quantidadeAtual < venda.quantidade!!) {
        //     throw IllegalStateException("Estoque insuficiente para o produto ${produto.nome}.")
        // }

        // estoque.quantidadeAtual -= venda.quantidade!!
        // estoqueRepository.save(estoque)

        // Aqui poderíamos chamar a lógica de verificação de reestoque
    }

    @Transactional
    fun atualizarEstoque(produtoId: Int, filialId: Int, dto: AtualizarEstoqueRequestDTO): EstoqueFilial {
        val produto = produtoRepository.findById(produtoId)
            .orElseThrow { NoSuchElementException("Produto com ID $produtoId não encontrado.") }
        val filial = filialRepository.findById(filialId)
            .orElseThrow { NoSuchElementException("Filial com ID $filialId não encontrada.") }

        val estoque = estoqueRepository.findByProdutoIdAndFilialId(produtoId, filialId) ?: EstoqueFilial().apply {
            this.produto = produto
            this.filial = filial
        }

        estoque.quantidadeAtual = dto.quantidadeAtual
        estoque.estoqueMinimo = dto.estoqueMinimo

        return estoqueRepository.save(estoque)
    }
}