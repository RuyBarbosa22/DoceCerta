package com.tcc.backend.dto

import jakarta.validation.constraints.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalTime

data class VendaFiltroDTO(
    @field:Pattern(
        regexp = "\\d{11}",
        message = "CPF deve conter exatamente 11 dígitos numéricos"
    )
    val cpfCliente: String? = null,

    @field:DateTimeFormat(pattern = "dd/MM/yyyy")
    val dataInicio: LocalDate? = null,

    @field:DateTimeFormat(pattern = "dd/MM/yyyy")
    val dataFim: LocalDate? = null,

    @field:Pattern(
        regexp = "DINHEIRO|DEBITO|PIX|CREDITO",
        message = "Forma de pagamento inválida (permitido: DINHEIRO, DEBITO, PIX, CREDITO)"
    )
    val formaPagamento: String? = null,

    val unidade: String? = null,

    @field:DateTimeFormat(pattern = "HH:mm:ss")
    val horario: LocalTime? = null,

    val categoriaProduto: String? = null,

    val fornecedorProduto: String? = null
) {
    @AssertTrue(message = "dataFim deve ser igual ou posterior a dataInicio")
    fun isPeriodoValido(): Boolean {
        if (dataInicio != null && dataFim != null) {
            return !dataFim.isBefore(dataInicio)
        }
        return true
    }
}
