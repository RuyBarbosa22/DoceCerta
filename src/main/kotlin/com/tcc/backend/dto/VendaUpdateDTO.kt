package com.tcc.backend.dto

import jakarta.validation.constraints.*
import java.time.LocalDateTime

data class VendaUpdateDTO(
    @field:PastOrPresent(message = "Data da venda não pode ser no futuro")
    val dataVenda: LocalDateTime?,

    @field:Positive(message = "Preço unitário deve ser maior que zero")
    val precoUnitario: Double?,

    @field:PositiveOrZero(message = "Valor total não pode ser negativo")
    val valorTotal: Double?,

    @field:Pattern(
        regexp = "DINHEIRO|DEBITO|PIX|CREDITO",
        message = "Forma de pagamento inválida"
    )
    val formaPagamento: String?,

    @field:Min(value = 0, message = "Desconto não pode ser negativo")
    @field:Max(value = 100, message = "Desconto máximo é 100%")
    val desconto: Double?,

    @field:Min(value = 1, message = "Quantidade mínima é 1")
    val quantidade: Int?,

    val unidade: String?,

    @field:Positive(message = "ID do produto deve ser um número positivo")
    val idProduto: Int?,

    @field:Pattern(
        regexp = "\\d{11}",
        message = "CPF deve conter exatamente 11 dígitos"
    )
    val cpfCliente: String?
)