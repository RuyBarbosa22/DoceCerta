package com.tcc.backend.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
@Table(name = "empresa")
class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "nome")
    var nome: String? = null

    @Column(name = "email")
    var email: String? = null

    @Size(max = 240)
    @Column(name = "descricao")
    var descricao: String? = null

    @Column(name = "cnpj", unique = true)
    var cnpj: String? = null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id")
    var endereco: Endereco? = null

    @OneToMany(
        mappedBy = "empresa",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties("empresa")
    var filiais: MutableList<Filial> = mutableListOf()
}
