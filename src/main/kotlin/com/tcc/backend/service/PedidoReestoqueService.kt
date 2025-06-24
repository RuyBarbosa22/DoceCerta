package com.tcc.backend.service

import com.tcc.backend.dto.CriarPedidoReestoqueRequestDTO
import com.tcc.backend.models.ItemPedidoReestoque
import com.tcc.backend.models.PedidoReestoque
import com.tcc.backend.models.enums.StatusPedido
import com.tcc.backend.repository.FilialRepository
import com.tcc.backend.repository.PedidoReestoqueRepository
import com.tcc.backend.repository.ProdutoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PedidoReestoqueService(
    private val pedidoRepository: PedidoReestoqueRepository,
    private val filialRepository: FilialRepository,
    private val produtoRepository: ProdutoRepository,
    private val notificationService: NotificationService
) {

    @Transactional
    fun criarPedido(filialId: Int, dto: CriarPedidoReestoqueRequestDTO): PedidoReestoque {
        val filial = filialRepository.findById(filialId)
            .orElseThrow { NoSuchElementException("Filial com ID $filialId não encontrada.") }

        val novoPedido = PedidoReestoque().apply {
            this.filialSolicitante = filial
            this.observacoes = dto.observacoes
        }

        val itensPedido = dto.itens.map { itemDTO ->
            val produto = produtoRepository.findById(itemDTO.produtoId)
                .orElseThrow { NoSuchElementException("Produto com ID ${itemDTO.produtoId} não encontrado no pedido.") }

            ItemPedidoReestoque().apply {
                this.produto = produto
                this.quantidadeSolicitada = itemDTO.quantidadeSolicitada
                this.pedidoReestoque = novoPedido
            }
        }

        novoPedido.itens.addAll(itensPedido)

        val pedidoSalvo = pedidoRepository.save(novoPedido)

        // Dispara a notificação após salvar
        notificationService.enviarNotificacaoDeNovoPedido(pedidoSalvo)

        return pedidoSalvo
    }

    @Transactional
    fun mudarStatus(pedidoId: Int, novoStatus: StatusPedido): PedidoReestoque {
        val pedido = pedidoRepository.findById(pedidoId)
            .orElseThrow { NoSuchElementException("Pedido com ID $pedidoId não encontrado.") }

        pedido.status = novoStatus
        // Poderíamos adicionar lógicas aqui, como notificar a filial quando o status muda para ENVIADO

        return pedidoRepository.save(pedido)
    }
}