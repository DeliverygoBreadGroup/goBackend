package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.mapper.PedidoMapper;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public List<PedidoResponse> cadastrar(PedidoRequest pedidoRequest){
        List<Pedido> listPedido = PedidoMapper.toListPedido(pedidoRequest);
        pedidoRepository.saveAll(listPedido);
        return PedidoMapper.toPedidoResponse(listPedido);
    }



}
