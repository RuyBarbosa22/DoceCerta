package com.tcc.backend.specification

import com.tcc.backend.dto.VendaFiltroDTO
import com.tcc.backend.models.*
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

object VendaSpecification {
    fun comFiltro(filtro: VendaFiltroDTO): Specification<Venda> {
        return Specification { root, query, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            filtro.cpfCliente?.let {
                val clienteJoin = root.join(Venda_.cliente, jakarta.persistence.criteria.JoinType.LEFT)
                predicates.add(criteriaBuilder.equal(clienteJoin.get(Cliente_.cpf), it))
            }
            filtro.dataInicio?.let {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Venda_.dataVenda), it.atStartOfDay()))
            }
            filtro.dataFim?.let {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Venda_.dataVenda), it.atTime(23, 59, 59)))
            }
            // Adicione outros predicados aqui para os demais filtros...

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }
}