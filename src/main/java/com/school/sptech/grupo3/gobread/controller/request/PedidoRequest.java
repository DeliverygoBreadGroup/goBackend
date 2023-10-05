package com.school.sptech.grupo3.gobread.controller.request;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record PedidoRequest(
        List<Integer> diasEntrega,
        String horaEntrega
) {}
