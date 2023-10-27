package com.school.sptech.grupo3.gobread.controller;


import com.school.sptech.grupo3.gobread.controller.request.EstoqueRequest;
import com.school.sptech.grupo3.gobread.controller.response.EstoqueResponse;
import com.school.sptech.grupo3.gobread.service.ItemComercioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-comercio")
@RequiredArgsConstructor
public class ItemComercioController {

    private final ItemComercioService itemComercioService;

    @PostMapping
    public ResponseEntity<EstoqueResponse> cadastrar(@RequestBody EstoqueRequest estoqueRequest){
        EstoqueResponse estoqueResponse = this.itemComercioService.cadastrar(estoqueRequest);
        return ResponseEntity.status(200).body(estoqueResponse);
    }

}
