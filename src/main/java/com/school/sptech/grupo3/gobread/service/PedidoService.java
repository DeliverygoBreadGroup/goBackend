package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository rep;
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;

//
//    public ResponseEntity<PedidoResponse> cadastrarPedido(PedidoRequest pedidoRequest) {
//        List<Integer> diasEntrega = pedidoRequest.diasEntrega();
//        Pedido pedido = modelMapper.from(pedidoRequest);
//        for (int i = 0; i < diasEntrega.size(); i++) {
//            int dia = diasEntrega.get(i);
//            switch (dia) {
//                case 1:
//                    pedido.setDiaEntrega("Segunda-Feira");
//                    rep.save(pedido);
//                    break;
//                case 2:
//                    pedido.setDiaEntrega("Terça-Feira");
//                    rep.save(pedido);
//                    break;
//                case 3:
//                    pedido.setDiaEntrega("Quarta-Feira");
//                    rep.save(pedido);
//                    break;
//                case 4:
//                    pedido.setDiaEntrega("Quinta-Feira");
//                    rep.save(pedido);
//                    break;
//                case 5:
//                    pedido.setDiaEntrega("Sexta-Feira");
//                    rep.save(pedido);
//                    break;
//            }
//
//        }
//
//    }
}
