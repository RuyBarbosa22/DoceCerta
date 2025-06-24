package com.tcc.backend.controller

import com.tcc.backend.dto.fornecedorDTO.FornecedorCreateDTO
import com.tcc.backend.dto.fornecedorDTO.FornecedorUpdateDTO
import com.tcc.backend.mappers.toEntity
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Fornecedor
import com.tcc.backend.repository.FornecedorRepository
import com.tcc.backend.repository.ProdutoRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fornecedores")
class FornecedorController(
    private val fornecedorRepository: FornecedorRepository,
    private val produtoRepository: ProdutoRepository
) {

    @PostMapping
    fun cadastrarFornecedor(
        @Valid @RequestBody dto: FornecedorCreateDTO
    ): ResponseEntity<Any> {
        if (fornecedorRepository.findByNome(dto.nome) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("error" to "Fornecedor com nome '${dto.nome}' já existe."))
        }

        val fornecedor = Fornecedor().apply {
            nome = dto.nome
            cnpj = dto.cnpj
            email = dto.email
            endereco = dto.endereco?.toEntity()
        }

        val salvo = fornecedorRepository.save(fornecedor)
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo.toResponseDTO())
    }

    @DeleteMapping("/{id}")
    fun removerFornecedor(@PathVariable id: Int): ResponseEntity<Any> {
        val fornecedor = fornecedorRepository.findById(id).orElse(null)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Fornecedor com id $id não encontrado."))

        fornecedorRepository.delete(fornecedor)
        return ResponseEntity.ok(mapOf("message" to "Fornecedor removido com sucesso."))
    }

    @PutMapping("/atualizar/{nome}")
    fun atualizarFornecedor(
        @PathVariable nome: String,
        @Valid @RequestBody dto: FornecedorUpdateDTO
    ): ResponseEntity<Any> {
        val fornecedor = fornecedorRepository.findByNome(nome)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Fornecedor com nome '$nome' não encontrado."))

        val nomeAntigo = fornecedor.nome

        dto.nome?.let { fornecedor.nome = it }
        dto.email?.let { fornecedor.email = it }
        dto.endereco?.let { fornecedor.endereco = it.toEntity() }

        val fornecedorAtualizado = fornecedorRepository.save(fornecedor)

        if (dto.nome != null && dto.nome != nomeAntigo) {
            val produtos = produtoRepository.findAllByFornecedorNome(nomeAntigo!!)
            produtos.forEach {
                it.fornecedor = fornecedorAtualizado
            }
            produtoRepository.saveAll(produtos)
        }

        return ResponseEntity.ok(fornecedorAtualizado.toResponseDTO())
    }
}