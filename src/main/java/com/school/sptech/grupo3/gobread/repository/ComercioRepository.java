package com.school.sptech.grupo3.gobread.repository;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComercioRepository extends JpaRepository<Comercio, Integer> {
    Optional<Comercio> findByEmailAndSenha(String email, String senha);

    Optional<Comercio> findByEmail(String email);

}
