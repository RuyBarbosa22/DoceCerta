package com.tcc.backend.models

import jakarta.persistence.*

@Entity
@Table(name = "fornecedor")
class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "nome", nullable = false)
    var nome: String? = null

    @Column(name = "cnpj", unique = true, nullable = false)
    var cnpj: String? = null

    @Column(name = "email")
    var email: String? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id")
    var endereco: Endereco? = null
}
