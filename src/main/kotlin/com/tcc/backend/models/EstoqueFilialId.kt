package com.tcc.backend.models

import java.io.Serializable

data class EstoqueFilialId(
    val produto: Int = 0,
    val filial: Int = 0
) : Serializable