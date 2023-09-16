package com.school.sptech.grupo3.gobread.controller.request;

public record EnderecoRequest(
        String cep,
        Integer numero,
        String complemento
) {}
