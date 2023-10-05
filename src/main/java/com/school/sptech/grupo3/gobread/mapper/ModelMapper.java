package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.request.ComercioRequest;
import com.school.sptech.grupo3.gobread.controller.request.EnderecoRequest;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    Comercio from(ComercioRequest comercioRequest);

    Cliente from(ClienteRequest clienteRequest);

    Endereco from(EnderecoRequest enderecoRequest);

    Pedido from(PedidoRequest pedidoRequest);

}
