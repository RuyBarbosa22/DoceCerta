package com.tcc.backend.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "produto")
class Produto {

    @Column(name = "id", unique = true)
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "nome", unique = true)
    var nome: String? = null

    @Column(name = "descricao")
    var descricao: String? = null

    @Column(name = "preco")
    var preco: Double? = null

    @Column(name = "custo")
    var custo: Double? = null

    @Column(name = "validade")
    var validade: LocalDate? = null

    @Column(name = "categoria")
    var categoria: String? = null

    @Column(name = "quantidade")
    var quantidade: Int? = null

    @ManyToOne
    @JoinColumn(name = "fk_fornecedor", referencedColumnName = "id")
    var fornecedor: Fornecedor? = null
}
