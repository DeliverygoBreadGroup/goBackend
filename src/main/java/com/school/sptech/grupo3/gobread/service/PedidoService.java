package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.controller.request.PedidoRequest;
import com.school.sptech.grupo3.gobread.controller.response.PedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Pedido;
import com.school.sptech.grupo3.gobread.estruturaPilhaFila.FilaObj;
import com.school.sptech.grupo3.gobread.estruturaPilhaFila.PilhaObj;
import com.school.sptech.grupo3.gobread.mapper.PedidoMapper;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import com.school.sptech.grupo3.gobread.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ComercioRepository comercioRepository;
    private PilhaObj pilhaPedidos = new PilhaObj(7);
    private FilaObj<Pedido> filaPedidos = new FilaObj<>(10, Pedido.class);
    List<String> diasDosPedidosFeitos = new ArrayList<>();

    public List<String> cadastrar(PedidoRequest pedidoRequest){
        Pedido pedido = PedidoMapper.toPedido(pedidoRequest);
        if(clienteRepository.existsById(pedido.getCliente().getId())){
            if(comercioRepository.existsById(pedido.getComercio().getId())){
                filaPedidos.insert(pedido);
                diasDosPedidosFeitos.add(pedido.getDiaEntrega());
                return diasDosPedidosFeitos;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercio não existe!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe!");
    }

    public PedidoResponse salvarPedidos() {
        Pedido pedido = new Pedido();
        int tamanho = filaPedidos.getTamanho();
        for (int i = 0; i < tamanho; i++) {
            pedido = (Pedido) filaPedidos.poll();
            this.pedidoRepository.save(pedido);
        }
        return PedidoMapper.toPedidoResponse(pedido);
    }


    public void deletar(int id) {
        Pedido pedido = this.pedidoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado")
        );
        pilhaPedidos.push(pedido);
        this.pedidoRepository.deleteById(id);
    }

    public PedidoResponse reverterDelete() {
        Pedido pedido = (Pedido) pilhaPedidos.pop();
        this.pedidoRepository.save(pedido);
        return PedidoMapper.toPedidoResponse(pedido);
    }
}
