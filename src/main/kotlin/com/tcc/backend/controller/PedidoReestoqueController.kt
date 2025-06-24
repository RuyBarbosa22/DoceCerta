package com.tcc.backend.controller

import com.tcc.backend.dto.CriarPedidoReestoqueRequestDTO
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.enums.StatusPedido
import com.tcc.backend.repository.PedidoReestoqueRepository
import com.tcc.backend.service.PedidoReestoqueService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PedidoReestoqueController(
    private val pedidoReestoqueService: PedidoReestoqueService,
    private val pedidoReestoqueRepository: PedidoReestoqueRepository // Para buscas simples
) {

    @PostMapping("/filiais/{filialId}/pedidos-reestoque")
    fun criarPedido(
        @PathVariable filialId: Int,
        @Valid @RequestBody dto: CriarPedidoReestoqueRequestDTO
    ): ResponseEntity<Any> {
        val pedido = pedidoReestoqueService.criarPedido(filialId, dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido.toResponseDTO())
    }

    @GetMapping("/pedidos-reestoque")
    fun listarPedidos(@RequestParam(required = false) status: StatusPedido?): ResponseEntity<Any> {
        val pedidos = if (status != null) {
            pedidoReestoqueRepository.findByStatus(status)
        } else {
            pedidoReestoqueRepository.findAll()
        }
        val pedidosDTO = pedidos.map { it.toResponseDTO() }
        return ResponseEntity.ok(pedidosDTO)
    }

    @GetMapping("/pedidos-reestoque/{id}")
    fun buscarPedidoPorId(@PathVariable id: Int): ResponseEntity<Any> {
        val pedido = pedidoReestoqueRepository.findById(id)
            .orElseThrow { NoSuchElementException("Pedido com ID $id não encontrado.") }
        return ResponseEntity.ok(pedido.toResponseDTO())
    }

    @PutMapping("/pedidos-reestoque/{id}/status")
    fun atualizarStatus(
        @PathVariable id: Int,
        @RequestBody statusUpdate: Map<String, String>
    ): ResponseEntity<Any> {
        val novoStatusStr = statusUpdate["status"]
            ?: return ResponseEntity.badRequest().body(mapOf("error" to "O campo 'status' é obrigatório."))

        val novoStatus = try {
            StatusPedido.valueOf(novoStatusStr.uppercase())
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body(mapOf("error" to "Status '$novoStatusStr' é inválido."))
        }

        val pedidoAtualizado = pedidoReestoqueService.mudarStatus(id, novoStatus)
        return ResponseEntity.ok(pedidoAtualizado.toResponseDTO())
    }
}