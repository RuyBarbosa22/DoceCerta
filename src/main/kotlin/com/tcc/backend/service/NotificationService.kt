package com.tcc.backend.service

import com.tcc.backend.models.PedidoReestoque
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NotificationService {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun enviarNotificacaoDeNovoPedido(pedido: PedidoReestoque) {
        // Lógica de simulação: apenas imprime no console
        val filial = pedido.filialSolicitante
        val empresaEmail = filial?.empresa?.email ?: "email.nao.cadastrado@matriz.com"

        logger.info("======================================================")
        logger.info("SIMULANDO ENVIO DE NOTIFICAÇÃO PARA A MATRIZ")
        logger.info("Destinatário: $empresaEmail")
        logger.info("Novo pedido de reestoque #${pedido.id} da filial '${filial?.nome}'")
        logger.info("Status: ${pedido.status}")
        logger.info("Itens Solicitados: ${pedido.itens.size}")
        logger.info("======================================================")

        // No futuro, aqui entraria a lógica real de envio de e-mail ou WhatsApp
    }
}