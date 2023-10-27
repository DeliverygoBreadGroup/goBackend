package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.ItemPedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.controller.response.ProdutoPedidoResponse;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    public static List<PedidoResponse> toPedidoResponse(List<Pedido> pedidos){
        List<PedidoResponse> pedidoResponse = pedidos.stream().map(PedidoMapper::toPedidoResponse).toList();
        return pedidoResponse;
    }

    public static PedidoResponse toPedidoResponse(Pedido pedido){
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setHorarioEntrega(pedido.getHorarioEntrega());
        pedidoResponse.setItensPedido(ItemPedidoMapper.toListItemPedidoResponse(pedido.getItensPedido()));
        pedidoResponse.setDiaEntrega(pedido.getDiaEntrega());
        return pedidoResponse;
    }

    public static List<Pedido> toListPedido(PedidoRequest pedidoRequest){
        int qtdPedidos = pedidoRequest.diasEntrega().size();
        List<Pedido> listPedido = new ArrayList<>();
        for(int i = 0; i < qtdPedidos; i++){
            Pedido pedido = new Pedido();
            pedido.setDiaEntrega(pedidoRequest.diasEntrega().get(i));
            pedido.setHorarioEntrega(pedidoRequest.horarioEntrega());
            pedido.setItensPedido(pedidoRequest.itensPedido());
            listPedido.add(pedido);
        }
        return listPedido;
    }
}
