package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Endereco;
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
         return clienteService.criarCliente(clienteRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable int id){
        return clienteService.buscarClientePorId(id);
    }

//    @GetMapping("/{fkEndereco}")
//    public ResponseEntity<Cliente> getClienteByFkEndereco(@PathVariable("fkEndereco") int fkEndereco) {
//        return clienteService.buscarEnderecoPorFk(fkEndereco);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable int id,@Valid @RequestBody ClienteRequest clienteRequest){
        return clienteService.atualizarCliente(id, clienteRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponse> deletarCliente(@PathVariable int id){
        return clienteService.deletarCliente(id);
    }


}
