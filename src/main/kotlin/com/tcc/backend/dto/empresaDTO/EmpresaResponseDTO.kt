package com.tcc.backend.dto.empresaDTO

import com.tcc.backend.dto.filialDTO.FilialSimplesResponseDTO
import com.tcc.backend.dto.enderecoDTO.EnderecoDTO

data class EmpresaResponseDTO(
    val id: Int,
    val nome: String?,
    val email: String?,
    val descricao: String?,
    val cnpj: String?,
    val endereco: EnderecoDTO?,
    val filiais: List<FilialSimplesResponseDTO>
)