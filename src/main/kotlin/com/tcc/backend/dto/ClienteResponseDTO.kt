package com.tcc.backend.dto

import java.time.LocalDate

data class ClienteResponseDTO(
    /** O identificador único do cliente, gerado pelo banco de dados. */
    val id: Int,

    /** O nome completo do cliente. */
    val nome: String?,

    /** O CPF do cliente. */
    val cpf: String?,

    /** O número de telefone ou celular para contato. */
    val numeroCtt: String?,

    /** A data de nascimento do cliente. */
    val dataNascimento: LocalDate?,

    /** O sexo declarado pelo cliente. */
    val sexo: String?,

    /** A data em que o registro do cliente foi criado no sistema. */
    val dataCadastro: LocalDate?,

    /** O endereço associado ao cliente, se houver. */
    val endereco: EnderecoDTO?
)