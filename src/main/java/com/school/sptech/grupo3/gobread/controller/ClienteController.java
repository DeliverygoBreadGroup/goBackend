package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.exceptions.ClienteNaoEncontradoException;
import com.school.sptech.grupo3.gobread.service.ClienteService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
         ClienteResponse clienteResponse =  clienteService.criarCliente(clienteRequest);
         return ResponseEntity.status(201).body(clienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable int id) throws ClienteNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.buscarClientePorId(id);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<ClienteResponse> loginCliente(@RequestBody LoginRequest loginRequest) throws ClienteNaoEncontradoException{
        ClienteResponse clienteResponse = clienteService.loginCliente(loginRequest);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable int id,@Valid @RequestBody ClienteRequest clienteRequest) throws ClienteNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.atualizarCliente(id, clienteRequest);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int id) throws ClienteNaoEncontradoException {
        boolean isDeleted =  clienteService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }


}
