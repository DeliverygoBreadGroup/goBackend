package com.school.sptech.grupo3.gobread.mapper;


import com.school.sptech.grupo3.gobread.controller.response.ComercioClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.entity.Comercio;

public class ComercioMapper {

    public static ComercioClienteResponse toComercioClienteResponse(Comercio comercio){
        ComercioClienteResponse comercioClienteResponse = new ComercioClienteResponse();
        comercioClienteResponse.setId(comercio.getId());
        comercioClienteResponse.setRazaoSocial(comercio.getRazaoSocial());
        return comercioClienteResponse;
    }

    public static ComercioResponse toComercioResponse(Comercio comercio){
        return ComercioResponse.builder()
                .email(comercio.getEmail())
                .razaoSocial(comercio.getRazaoSocial())
                .responsavel(comercio.getResponsavel())
                .telefone(comercio.getTelefone())
                .endereco(comercio.getEndereco())
                .pedidos(PedidoMapper.toListPedidoComercioResponse(comercio.getPedidos()))
                .build();
    }
}
