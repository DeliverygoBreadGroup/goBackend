package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.response.ClienteComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Cliente;

public class ClienteMapper {

    public static ClienteResponse clienteToClienteResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .endereco(cliente.getEndereco())
                .pedidos(PedidoMapper.toListPedidoClienteResponse(cliente.getPedidos()))
                .build();
    }

    public static ClienteResponse clienteToClienteResponseSemPedidos(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .endereco(cliente.getEndereco())
                .build();
    }


    public static ClienteComercioResponse toClienteComercioResponse(Cliente cliente){
        ClienteComercioResponse clienteResponse = new ClienteComercioResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setCpf(cliente.getCpf());
        clienteResponse.setTelefone(cliente.getTelefone());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setEndereco(cliente.getEndereco());
        return clienteResponse;
    }
}
