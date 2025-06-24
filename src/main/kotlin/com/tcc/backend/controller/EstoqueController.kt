package com.tcc.backend.controller

import com.tcc.backend.dto.AtualizarEstoqueRequestDTO
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.service.EstoqueService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/estoque")
class EstoqueController(
    private val estoqueService: EstoqueService
) {

    @PutMapping("/filiais/{filialId}/produtos/{produtoId}")
    fun atualizarEstoque(
        @PathVariable filialId: Int,
        @PathVariable produtoId: Int,
        @Valid @RequestBody dto: AtualizarEstoqueRequestDTO
    ): ResponseEntity<Any> {
        val estoqueAtualizado = estoqueService.atualizarEstoque(produtoId, filialId, dto)
        return ResponseEntity.ok(estoqueAtualizado.toResponseDTO())
    }
}