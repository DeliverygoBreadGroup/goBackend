package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.ComercioResponse;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.ItemPedido;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.mapper.PedidoMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.repository.ItemPedidoRepository;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ComercioRepository comercioRepository;

    public PedidoResponse cadastrar(PedidoRequest pedidoRequest){
        Pedido pedido = PedidoMapper.toPedido(pedidoRequest);
        if(clienteRepository.existsById(pedido.getCliente().getId())){
            if(comercioRepository.existsById(pedido.getComercio().getId())){
                pedidoRepository.save(pedido);
                return PedidoMapper.toPedidoResponse(pedido);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio não existe!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe!");
    }



}
