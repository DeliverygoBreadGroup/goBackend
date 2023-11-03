package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

import java.util.List;

@Builder
public record ComercioResponse(

        String razaoSocial,
        String responsavel,
        String email,
        String telefone,
        Endereco endereco,
        List<PedidoComercioResponse> pedidos
) {}
