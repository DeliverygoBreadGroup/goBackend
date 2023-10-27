package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.service.PedidoService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<List<PedidoResponse>> cadastrar(@RequestBody PedidoRequest pedidoRequest){
        List<PedidoResponse> listaResponse = this.pedidoService.cadastrar(pedidoRequest);
        return ResponseEntity.status(201).body(listaResponse);
    }


}
