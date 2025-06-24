package com.tcc.backend.controller

import com.tcc.backend.dto.filialDTO.FilialDTO
import com.tcc.backend.mappers.toEntity
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Filial
import com.tcc.backend.repository.EmpresaRepository
import com.tcc.backend.repository.FilialRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/filiais")
class FilialController(
    private val filialRepository: FilialRepository,
    private val empresaRepository: EmpresaRepository
) {

    @PostMapping("/cadastro/{cnpj}")
    fun criarFilial(
        @PathVariable cnpj: String,
        @Valid @RequestBody filialDTO: FilialDTO
    ): ResponseEntity<Any> {
        val empresa = empresaRepository.findByCnpj(cnpj)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Empresa com CNPJ $cnpj não encontrada."))

        val novaFilial = Filial().apply {
            this.empresa = empresa
            this.nome = filialDTO.nome
            this.descricao = filialDTO.descricao
            this.endereco = filialDTO.endereco.toEntity()
            this.numero = filialDTO.numero
            this.matriz = filialDTO.matriz
        }

        val filialSalva = filialRepository.save(novaFilial)
        return ResponseEntity.status(HttpStatus.CREATED).body(filialSalva.toResponseDTO())
    }

    @DeleteMapping("/empresa/{cnpj}/filial/{nome}")
    fun deletarFilial(
        @PathVariable cnpj: String,
        @PathVariable nome: String
    ): ResponseEntity<Any> {
        val filial = filialRepository.findByEmpresaCnpjAndNome(cnpj, nome)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Filial '$nome' não encontrada para CNPJ $cnpj."))

        filialRepository.delete(filial)
        return ResponseEntity.ok(mapOf("message" to "Filial '$nome' deletada com sucesso."))
    }

    @GetMapping("/listar/{cnpj}")
    fun listarFiliais(
        @PathVariable cnpj: String
    ): ResponseEntity<Any> {
        val empresa = empresaRepository.findByCnpj(cnpj)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Empresa com CNPJ $cnpj não encontrada."))

        val filiaisDTO = empresa.filiais.map { it.toResponseDTO() }
        return ResponseEntity.ok(filiaisDTO)
    }

    @PutMapping("/empresa/{cnpj}/filial/{nome}/nova-matriz")
    fun definirNovaMatriz(
        @PathVariable cnpj: String,
        @PathVariable nome: String
    ): ResponseEntity<Any> {
        val empresa = empresaRepository.findByCnpj(cnpj)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Empresa com CNPJ $cnpj não encontrada."))

        val filialNovaMatriz = empresa.filiais.find { it.nome == nome }
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Filial '$nome' não encontrada para CNPJ $cnpj."))

        empresa.filiais.filter { it.matriz }.forEach {
            it.matriz = false
            filialRepository.save(it)
        }

        filialNovaMatriz.matriz = true
        val filialAtualizada = filialRepository.save(filialNovaMatriz)

        return ResponseEntity.ok(filialAtualizada.toResponseDTO())
    }
}