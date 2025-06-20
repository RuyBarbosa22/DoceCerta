package com.tcc.backend.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "cliente")
class Cliente {

    @Column(name = "id")
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "nome")
    var nome: String? = null

    @Column(name = "cpf", unique = true)
    var cpf: String? = null

    @Column(name = "numeroCtt")
    var numeroCtt: String? = null

    @Column(name = "data_nascimento")
    var dataNascimento: LocalDate? = null

    @Column(name = "sexo")
    var sexo: String? = null

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    var endereco: Endereco? = null

    @Column(name = "data_cadastro")
    var dataCadastro: LocalDate? = null
}