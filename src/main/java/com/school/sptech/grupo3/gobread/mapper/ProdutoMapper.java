package com.school.sptech.grupo3.gobread.mapper;

import com.school.sptech.grupo3.gobread.controller.response.ProdutoPedidoResponse;
import com.school.sptech.grupo3.gobread.entity.Produto;

public class ProdutoMapper {
    public static ProdutoPedidoResponse toProdutoResponse(Produto produto){
        ProdutoPedidoResponse produtoResponse = new ProdutoPedidoResponse();
        produtoResponse.setId(produto.getId());
        return produtoResponse;
    }
}
