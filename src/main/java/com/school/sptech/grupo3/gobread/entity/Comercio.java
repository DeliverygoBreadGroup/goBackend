package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
public class Comercio{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String senha;
    private String telefone;
    private String razaoSocial;
    private String responsavel;
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fkEndereco", referencedColumnName = "id")
    private Endereco endereco;

    public Comercio(Integer id, String email, String senha, String telefone, String razaoSocial, String responsavel, String cnpj, Endereco endereco) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.razaoSocial = razaoSocial;
        this.responsavel = responsavel;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public Comercio atualizarEndereco(AddressViaCep addressViaCep) {
        return this.toBuilder()
                .endereco(Endereco.builder()
                        .cep(this.endereco.getCep())
                        .rua(addressViaCep.logradouro())
                        .numero(this.endereco.getNumero())
                        .complemento(this.endereco.getComplemento())
                        .bairro(addressViaCep.bairro())
                        .cidade(addressViaCep.localidade())
                        .estado(addressViaCep.uf())
                        .build())
                .build();
    }

    @Override
    public String toString() {
        return "Comercio{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco=" + endereco +
                "} ";
    }
}
