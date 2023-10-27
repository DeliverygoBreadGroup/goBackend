package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.*;
import com.school.sptech.grupo3.gobread.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    Comercio from(ComercioRequest comercioRequest);

    Cliente from(ClienteRequest clienteRequest);

    Endereco from(EnderecoRequest enderecoRequest);


}
