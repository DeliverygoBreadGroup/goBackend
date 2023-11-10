package com.school.sptech.grupo3.gobread.controller.response;

import com.school.sptech.grupo3.gobread.entity.Endereco;
import lombok.Builder;

import java.util.List;

@Builder
public record ClienteResponse(
    Integer id,
    String nome,
    String telefone,
    String email,
    Endereco endereco,

    List<PedidoClienteResponse> pedidos
) {}
