package com.school.sptech.grupo3.gobread.service;


import com.school.sptech.grupo3.gobread.controller.request.EstoqueRequest;
import com.school.sptech.grupo3.gobread.controller.response.EstoqueResponse;
import com.school.sptech.grupo3.gobread.controller.response.ItemComercioResponse;
import com.school.sptech.grupo3.gobread.entity.ItemComercio;
import com.school.sptech.grupo3.gobread.mapper.ItemComercioMapper;
import com.school.sptech.grupo3.gobread.repository.ItemComercioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemComercioService {

    private final ItemComercioRepository itemComercioRepository;


    public EstoqueResponse cadastrar(EstoqueRequest estoqueRequest){
        List<ItemComercio> listaItens = ItemComercioMapper.toListItemComercio(estoqueRequest.getItensComercio());
        itemComercioRepository.saveAll(listaItens);
        List<ItemComercioResponse> listaResponse = ItemComercioMapper.toListItemComercioResponse(listaItens);
        EstoqueResponse estoqueResponse = new EstoqueResponse(listaResponse);
        return estoqueResponse;
    }

}
