package com.tcc.backend.controller

import com.tcc.backend.dto.vendaDTO.VendaDTO
import com.tcc.backend.dto.vendaDTO.VendaFiltroDTO
import com.tcc.backend.dto.vendaDTO.VendaUpdateDTO
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Venda
import com.tcc.backend.repository.ClienteRepository
import com.tcc.backend.repository.FilialRepository
import com.tcc.backend.repository.ProdutoRepository
import com.tcc.backend.repository.VendaRepository
import com.tcc.backend.service.EstoqueService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalTime

@RestController
@RequestMapping("/api/vendas")
class VendaController(
    private val vendaRepository: VendaRepository,
    private val produtoRepository: ProdutoRepository,
    private val clienteRepository: ClienteRepository,
    private val filialRepository: FilialRepository,
    private val estoqueService: EstoqueService
) {

    @PostMapping
    fun cadastrarVenda(@Valid @RequestBody vendaDTO: VendaDTO): ResponseEntity<Any> {
        val produto = produtoRepository.findById(vendaDTO.idProduto)
            .orElseThrow { IllegalArgumentException("Produto com ID ${vendaDTO.idProduto} não encontrado.") }

        val cliente = vendaDTO.cpfCliente?.let { cpf ->
            clienteRepository.findByCpf(cpf)
                ?: throw IllegalArgumentException("Cliente com CPF $cpf não encontrado.")
        }

        val venda = Venda().apply {
            dataVenda = vendaDTO.dataVenda
            precoUnitario = vendaDTO.precoUnitario
            valorTotal = vendaDTO.valorTotal
            formaPagamento = vendaDTO.formaPagamento
            desconto = vendaDTO.desconto
            quantidade = vendaDTO.quantidade
            unidade = vendaDTO.unidade
            this.produto = produto
            this.cliente = cliente
            this.filial = filial
        }

        val salvo = vendaRepository.save(venda)
        try {
            estoqueService.darBaixaEstoque(salvo)
        } catch (e: IllegalStateException) {
            // Se não houver estoque, retorna erro
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to e.message))
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo.toResponseDTO())
    }

    @PutMapping("/{id}")
    fun atualizarVenda(@PathVariable id: Int, @Valid @RequestBody vendaDTO: VendaUpdateDTO): ResponseEntity<Any> {
        val venda = vendaRepository.findById(id)
            .orElseThrow { NoSuchElementException("Venda com ID $id não encontrada.") }

        vendaDTO.dataVenda?.let { venda.dataVenda = it }
        vendaDTO.precoUnitario?.let { venda.precoUnitario = it }
        vendaDTO.valorTotal?.let { venda.valorTotal = it }
        vendaDTO.formaPagamento?.let { venda.formaPagamento = it }
        vendaDTO.desconto?.let { venda.desconto = it }
        vendaDTO.quantidade?.let { venda.quantidade = it }
        vendaDTO.unidade?.let { venda.unidade = it }

        vendaDTO.idProduto?.let { pid ->
            venda.produto = produtoRepository.findById(pid)
                .orElseThrow { IllegalArgumentException("Produto com ID $pid não encontrado.") }
        }
        vendaDTO.cpfCliente?.let { cpf ->
            venda.cliente = clienteRepository.findByCpf(cpf)
                ?: throw IllegalArgumentException("Cliente com CPF $cpf não encontrado.")
        }

        val atualizado = vendaRepository.save(venda)
        return ResponseEntity.ok(atualizado.toResponseDTO())
    }

    @DeleteMapping("/{id}")
    fun removerVenda(@PathVariable id: Int): ResponseEntity<Any> {
        if (!vendaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Venda com ID $id não encontrada."))
        }
        vendaRepository.deleteById(id)
        return ResponseEntity.ok(mapOf("message" to "Venda removida com sucesso."))
    }

    @GetMapping
    fun filtrarVendas(@Valid @ModelAttribute filtro: VendaFiltroDTO): ResponseEntity<Any> {
        val dataInicio = filtro.dataInicio?.atStartOfDay()
        val dataFim = filtro.dataFim?.atTime(LocalTime.MAX) // Garante pegar o dia inteiro
        val horario = filtro.horario?.toString()

        val resultados = vendaRepository.filtrarVendas(
            cpfCliente = filtro.cpfCliente,
            dataInicio = dataInicio,
            dataFim = dataFim,
            formaPagamento = filtro.formaPagamento,
            unidade = filtro.unidade,
            horario = horario,
            categoriaProduto = filtro.categoriaProduto,
            fornecedorProduto = filtro.fornecedorProduto
        )

        val resultadosDTO = resultados.map { it.toResponseDTO() }
        return ResponseEntity.ok(resultadosDTO)
    }
}