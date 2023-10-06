package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.entity.Endereco;
import com.school.sptech.grupo3.gobread.exceptions.ClienteNaoEncontradoException;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;
    private final EnderecoService enderecoService;
    private final ClienteRepository rep;

    public ClienteResponse buscarClientePorId(int id) throws ClienteNaoEncontradoException {
      Cliente cliente = this.rep.findById(id).orElseThrow(
              () -> new ClienteNaoEncontradoException());
      ClienteResponse clienteResponse = responseMapper.from(cliente);
      return clienteResponse;
    }

    public ClienteResponse loginCliente(LoginRequest loginRequest) throws ClienteNaoEncontradoException {
        Cliente cliente = this.rep.findByEmailAndSenha(loginRequest.email(), loginRequest.senha()).orElseThrow(
                ClienteNaoEncontradoException::new
        );
        ClienteResponse clienteResponse = responseMapper.from(cliente);
        return clienteResponse;
    }

    public ClienteResponse criarCliente(ClienteRequest clienteRequest) {
        final Cliente cliente = modelMapper.from(clienteRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
        final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
        rep.save(clienteEnderecoAtualizado);
        final ClienteResponse clienteResponse = responseMapper.from(clienteEnderecoAtualizado);
        return clienteResponse;
    }

    public ClienteResponse atualizarCliente(int id, ClienteRequest clienteRequest) throws ClienteNaoEncontradoException {
        if(rep.existsById(id)){
            final Cliente cliente = modelMapper.from(clienteRequest);
            final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
            final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
            clienteEnderecoAtualizado.setId(id);
            clienteEnderecoAtualizado.getEndereco().setId(id);
            rep.save(clienteEnderecoAtualizado);
            final ClienteResponse clienteResponse = responseMapper.from(clienteEnderecoAtualizado);
            return clienteResponse;
        }
        throw new ClienteNaoEncontradoException();
    }

    public boolean deletarCliente(int id) throws ClienteNaoEncontradoException {
        if(rep.existsById(id)){
            this.rep.deleteById(id);
            return true;
        }
        throw new ClienteNaoEncontradoException();
    }

}
