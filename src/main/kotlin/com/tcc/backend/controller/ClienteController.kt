package com.tcc.backend.controller

import com.tcc.backend.dto.AtualizarContatoDTO
import com.tcc.backend.dto.ClienteResponseDTO
import com.tcc.backend.dto.CriarClienteRequestDTO
import com.tcc.backend.dto.EnderecoDTO
import com.tcc.backend.mappers.toEntity
import com.tcc.backend.mappers.toResponseDTO
import com.tcc.backend.models.Cliente
import com.tcc.backend.repository.ClienteRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/clientes")
class ClienteController(
    private val clienteRepository: ClienteRepository
) {

    @PostMapping("/cadastrar")
    fun cadastrarCliente(@Valid @RequestBody clienteDTO: CriarClienteRequestDTO): ResponseEntity<Any> {
        if (clienteRepository.findByCpf(clienteDTO.cpf) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(mapOf("error" to "Cliente com CPF ${clienteDTO.cpf} já cadastrado."))
        }

        val novoCliente = Cliente().apply {
            nome = clienteDTO.nome
            cpf = clienteDTO.cpf
            numeroCtt = clienteDTO.numeroCtt
            dataNascimento = clienteDTO.dataNascimento
            sexo = clienteDTO.sexo
            dataCadastro = LocalDate.now()
            endereco = clienteDTO.endereco.toEntity()
        }

        val clienteSalvo = clienteRepository.save(novoCliente)
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo.toResponseDTO())
    }

    @DeleteMapping("/remover/{cpf}")
    fun removerCliente(@PathVariable cpf: String): ResponseEntity<Any> {
        val cliente = clienteRepository.findByCpf(cpf)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Cliente com CPF $cpf não encontrado."))

        clienteRepository.delete(cliente)
        return ResponseEntity.ok(mapOf("message" to "Cliente com CPF $cpf removido com sucesso."))
    }

    @PutMapping("/atualizar-endereco/{cpf}")
    fun atualizarEndereco(
        @PathVariable cpf: String,
        @Valid @RequestBody enderecoDTO: EnderecoDTO
    ): ResponseEntity<Any> {
        val cliente = clienteRepository.findByCpf(cpf)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Cliente com CPF $cpf não encontrado."))

        cliente.endereco = enderecoDTO.toEntity()
        val clienteAtualizado = clienteRepository.save(cliente)

        return ResponseEntity.ok(clienteAtualizado.toResponseDTO())
    }

    @PutMapping("/atualizar-contato/{cpf}")
    fun atualizarContato(
        @PathVariable cpf: String,
        @Valid @RequestBody contatoDTO: AtualizarContatoDTO
    ): ResponseEntity<Any> {
        val cliente = clienteRepository.findByCpf(cpf)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "Cliente com CPF $cpf não encontrado."))

        cliente.numeroCtt = contatoDTO.numeroCtt
        val clienteAtualizado = clienteRepository.save(cliente)

        return ResponseEntity.ok(clienteAtualizado.toResponseDTO())
    }
}