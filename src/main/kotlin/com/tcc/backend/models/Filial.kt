package com.tcc.backend.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "filial")
class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "nome")
    var nome: String? = null

    @Column(name = "descricao")
    var descricao: String? = null

    @Column(name = "numero")
    var numero: String? = null

    @Column(name = "matriz")
    var matriz: Boolean = false

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_empresa", referencedColumnName = "id")
    @JsonIgnoreProperties("filiais")
    var empresa: Empresa? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id")
    var endereco: Endereco? = null
}
