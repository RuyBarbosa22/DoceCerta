package com.tcc.backend.controller

import com.tcc.backend.dto.empresaDTO.EmpresaDTO
import com.tcc.backend.dto.empresaDTO.EmpresaResponseDTO
import com.tcc.backend.mappers.toEntity
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Empresa
import com.tcc.backend.repository.EmpresaRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresas")
class EmpresaController(
    private val empresaRepository: EmpresaRepository
) {

    @GetMapping("/buscar/{cnpj}")
    fun buscarPorCnpj(@PathVariable cnpj: String): ResponseEntity<EmpresaResponseDTO> {
        val empresa = empresaRepository.findByCnpj(cnpj)
        return if (empresa != null) {
            ResponseEntity.ok(empresa.toResponseDTO())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/cadastrar")
    // ALTERADO: A assinatura do método agora usa a sua EmpresaDTO
    fun criarEmpresa(@Valid @RequestBody empresaDTO: EmpresaDTO): ResponseEntity<Any> {
        if (empresaRepository.findByCnpj(empresaDTO.cnpj) != null) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(mapOf("error" to "Empresa com CNPJ ${empresaDTO.cnpj} já existe."))
        }

        val novaEmpresa = Empresa().apply {
            nome = empresaDTO.nome
            cnpj = empresaDTO.cnpj
            email = empresaDTO.email
            descricao = empresaDTO.descricao
            endereco = empresaDTO.endereco.toEntity()
        }

        val salva = empresaRepository.save(novaEmpresa)
        return ResponseEntity.status(HttpStatus.CREATED).body(salva.toResponseDTO())
    }

    @DeleteMapping("/remover/{cnpj}")
    fun removerEmpresaPorCnpj(@PathVariable cnpj: String): ResponseEntity<Any> {
        val empresa = empresaRepository.findByCnpj(cnpj)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Empresa com CNPJ $cnpj não encontrada."))

        empresaRepository.delete(empresa)
        return ResponseEntity.ok(mapOf("message" to "Empresa removida com sucesso."))
    }
}