package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;

  // Método da interface implementada
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Cliente> usuarioOpt = clienteRepository.findByEmail(username);

    if (usuarioOpt.isEmpty()) {

      throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
    }

    return usuarioOpt.get();
  }
}
