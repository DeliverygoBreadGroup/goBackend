package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.service.ClienteService;
import com.school.sptech.grupo3.gobread.service.ComercioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comercio")
@RequiredArgsConstructor
public class ComercioController {

    private final ComercioService comercioService;

    @PostMapping
    public ResponseEntity<ComercioResponse> cadastrarComercio(@RequestBody ComercioRequest comercioRequest) {
        return comercioService.criarComercio(comercioRequest);
    }



}
