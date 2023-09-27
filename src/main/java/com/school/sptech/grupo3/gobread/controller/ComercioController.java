package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.service.ClienteService;
import com.school.sptech.grupo3.gobread.service.ComercioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comercios")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @PostMapping
    public ResponseEntity<ComercioResponse> cadastrarComercio(@Valid @RequestBody ComercioRequest comercioRequest) {
        return comercioService.criarComercio(comercioRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComercioResponse> buscarPorId(@PathVariable int id){
        return comercioService.buscarComercioPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComercioResponse> atualizarComercio(@PathVariable int id, @Valid @RequestBody ComercioRequest comercioRequest){
        return comercioService.atualizarComercio(id, comercioRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComercioResponse> deletarComercio(@PathVariable int id){
        return comercioService.deletarComercio(id);
    }



}
