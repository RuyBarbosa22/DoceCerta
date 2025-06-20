package com.tcc.backend.dto

import jakarta.validation.constraints.*
import java.time.LocalDateTime

data class VendaDTO(
    @field:NotNull(message = "Data e hora da venda são obrigatórias")
    @field:PastOrPresent(message = "Data da venda não pode ser no futuro")
    val dataVenda: LocalDateTime,

    @field:NotNull(message = "Preço unitário é obrigatório")
    @field:Positive(message = "Preço unitário deve ser maior que zero")
    val precoUnitario: Double,

    @field:NotNull(message = "Valor total é obrigatório")
    @field:PositiveOrZero(message = "Valor total não pode ser negativo")
    val valorTotal: Double,

    @field:NotBlank(message = "Forma de pagamento é obrigatória")
    @field:Pattern(
        regexp = "DINHEIRO|DEBITO|PIX|CREDITO",
        message = "Forma de pagamento inválida (permitido: DINHEIRO, DEBITO, PIX, CREDITO)"
    )
    val formaPagamento: String,

    @field:Min(value = 0, message = "Desconto não pode ser negativo")
    @field:Max(value = 100, message = "Desconto máximo é 100%")
    val desconto: Double = 0.0,

    @field:NotNull(message = "Quantidade é obrigatória")
    @field:Min(value = 1, message = "Quantidade mínima é 1")
    val quantidade: Int,

    @field:NotBlank(message = "Unidade de medida é obrigatória")
    val unidade: String,

    @field:NotNull(message = "ID do produto é obrigatório")
    @field:Positive(message = "ID do produto deve ser um número positivo")
    val idProduto: Int,

    @field:Pattern(
        regexp = "\\d{11}",
        message = "CPF deve conter exatamente 11 dígitos numéricos"
    )
    val cpfCliente: String? = null  // opcional
)
