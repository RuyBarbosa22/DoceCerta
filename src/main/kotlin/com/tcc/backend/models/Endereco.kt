package com.tcc.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "endereco")
class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "rua")
    var rua: String? = null

    @Column(name = "numero")
    var numero: String? = null

    @Column(name = "bairro")
    var bairro: String? = null

    @Column(name = "cidade")
    var cidade: String? = null

    @Column(name = "estado")
    var estado: String? = null

    @Column(name = "cep")
    var cep: String? = null
}
