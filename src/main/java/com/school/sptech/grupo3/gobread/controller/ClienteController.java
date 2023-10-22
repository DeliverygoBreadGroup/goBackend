package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginResponse;
import com.school.sptech.grupo3.gobread.exceptions.UsuarioNaoEncontradoException;
import com.school.sptech.grupo3.gobread.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
         ClienteResponse clienteResponse =  clienteService.criarCliente(clienteRequest);
         return ResponseEntity.status(201).body(clienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable int id) throws UsuarioNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.buscarClientePorId(id);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCliente(@RequestBody LoginRequest loginRequest) throws UsuarioNaoEncontradoException {
        LoginResponse response = clienteService.autenticar(loginRequest);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable int id,@Valid @RequestBody ClienteRequest clienteRequest) throws UsuarioNaoEncontradoException {
        ClienteResponse clienteResponse = clienteService.atualizarCliente(id, clienteRequest);
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int id) throws UsuarioNaoEncontradoException {
        boolean isDeleted =  clienteService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }

}
