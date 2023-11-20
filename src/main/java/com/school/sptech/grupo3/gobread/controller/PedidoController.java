package com.school.sptech.grupo3.gobread.controller;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.service.PedidoService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<List<String>> cadastrar(@RequestBody PedidoRequest pedidoRequest){
        List<String> listaDeDias = this.pedidoService.cadastrar(pedidoRequest);
        return ResponseEntity.status(200).body(listaDeDias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable int id){
        this.pedidoService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/reverter-delete")
    public ResponseEntity<PedidoResponse> reverterDelete(){
        PedidoResponse pedidoResponse = this.pedidoService.reverterDelete();
        return ResponseEntity.status(201).body(pedidoResponse);
    }

    @PostMapping("/salvar-pedidos")
    public ResponseEntity<PedidoResponse> salvarPedidos(){
        PedidoResponse pedidoResponse = this.pedidoService.salvarPedidos();
        return ResponseEntity.status(201).body(pedidoResponse);
    }

}
