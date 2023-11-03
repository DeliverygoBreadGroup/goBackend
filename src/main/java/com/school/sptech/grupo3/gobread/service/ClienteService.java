package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import com.school.sptech.grupo3.gobread.controller.request.ClienteRequest;
import com.school.sptech.grupo3.gobread.controller.request.LoginRequest;
import com.school.sptech.grupo3.gobread.controller.response.ClienteResponse;
import com.school.sptech.grupo3.gobread.controller.response.LoginResponse;
import com.school.sptech.grupo3.gobread.exceptions.UsuarioNaoEncontradoException;
import com.school.sptech.grupo3.gobread.mapper.ClienteMapper;
import com.school.sptech.grupo3.gobread.mapper.ModelMapper;
import com.school.sptech.grupo3.gobread.mapper.ResponseMapper;
import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.security.GerenciadorTokenJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ModelMapper modelMapper;
    private final ResponseMapper responseMapper;
    private final EnderecoService enderecoService;
    private final ClienteRepository rep;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public ClienteResponse buscarClientePorId(int id) throws UsuarioNaoEncontradoException {
        Cliente cliente = this.rep.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException());
        ClienteResponse clienteResponse = ClienteMapper.clienteToClienteResponse(cliente);
        return clienteResponse;
    }

    public LoginResponse autenticar(LoginRequest usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.email(), usuarioLoginDto.senha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Cliente usuarioAutenticado =
                rep.findByEmail(usuarioLoginDto.email())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);
        LoginResponse response = new LoginResponse(token);
        return response;
    }

    public ClienteResponse criarCliente(ClienteRequest clienteRequest) {
        final Cliente cliente = modelMapper.from(clienteRequest);
        final AddressViaCep enderecoViaCep = enderecoService.buscarEnderecoViaCep(cliente.getEndereco().getCep());
        final Cliente clienteEnderecoAtualizado = cliente.atualizarEndereco(enderecoViaCep);
        String senhaCriptografada = passwordEncoder.encode(clienteEnderecoAtualizado.getSenha());
        clienteEnderecoAtualizado.setSenha(senhaCriptografada);
        rep.save(clienteEnderecoAtualizado);
        final ClienteResponse clienteResponse = ClienteMapper.clienteToClienteResponseSemPedidos(clienteEnderecoAtualizado);
        return clienteResponse;
    }

    public ClienteResponse atualizarCliente(int id, ClienteRequest clienteRequest) throws UsuarioNaoEncontradoException {
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
        throw new UsuarioNaoEncontradoException();
    }

    public boolean deletarCliente(int id) throws UsuarioNaoEncontradoException {
        if(rep.existsById(id)){
            this.rep.deleteById(id);
            return true;
        }
        throw new UsuarioNaoEncontradoException();
    }




}
