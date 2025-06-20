package com.tcc.backend.controller

import com.tcc.backend.dto.ProdutoDTO
import com.tcc.backend.dto.ProdutoResponseDTO
import com.tcc.backend.dto.ProdutoUpdateDTO
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Produto
import com.tcc.backend.repository.FornecedorRepository
import com.tcc.backend.repository.ProdutoRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/produtos")
class ProdutoController(
    private var fornecedorRepository: FornecedorRepository,
    private var produtoRepository: ProdutoRepository
) {

    @PostMapping("/cadastrar")
    fun cadastrarProduto(@Valid @RequestBody dto: ProdutoDTO): ResponseEntity<Any> {
        val fornecedor = fornecedorRepository.findByNome(dto.nomeFornecedor)
            ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(mapOf("error" to "Fornecedor com nome '${dto.nomeFornecedor}' não encontrado."))

        val produto = Produto().apply {
            nome = dto.nome
            descricao = dto.descricao
            preco = dto.preco
            custo = dto.custo
            validade = dto.validade
            categoria = dto.categoria
            quantidade = dto.quantidade
            this.fornecedor = fornecedor
        }
        val salvo = produtoRepository.save(produto)
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo.toResponseDTO())
    }

    @DeleteMapping("/remover/{id}")
    fun removerProduto(@PathVariable id: Int): ResponseEntity<Any> {
        val produto = produtoRepository.findById(id)

        return if (produto.isPresent) {
            produtoRepository.deleteById(id)
            ResponseEntity.ok(mapOf("message" to "Produto removido com sucesso."))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Produto não encontrado."))
        }
    }

    @PutMapping("/atualizar/{id}")
    fun atualizarProduto(
        @PathVariable id: Int,
        @Valid @RequestBody dto: ProdutoUpdateDTO
    ): ResponseEntity<Any> {
        val produto = produtoRepository.findById(id).orElse(null)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Produto com id '$id' não encontrado."))

        dto.nome?.let { produto.nome = it }
        dto.descricao?.let { produto.descricao = it }
        dto.preco?.let { produto.preco = it }
        dto.custo?.let { produto.custo = it }
        dto.validade?.let { produto.validade = it }
        dto.categoria?.let { produto.categoria = it }
        dto.quantidade?.let { produto.quantidade = it }

        dto.nomeFornecedor?.let { nomeFornecedor ->
            val fornecedor = fornecedorRepository.findByNome(nomeFornecedor)
                ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(mapOf("error" to "Fornecedor com nome '$nomeFornecedor' não encontrado."))
            produto.fornecedor = fornecedor
        }

        val atualizado = produtoRepository.save(produto)
        return ResponseEntity.ok(atualizado.toResponseDTO())
    }
}