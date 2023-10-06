package com.school.sptech.grupo3.gobread.entity;

import com.school.sptech.grupo3.gobread.apiviacep.AddressViaCep;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Entity
public class Comercio {
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
//    @ManyToMany
////    @JoinTable(
////            name = "comercio_produto",
////            joinColumns = @JoinColumn(name = "comercio_id"),
////            inverseJoinColumns = @JoinColumn(name = "produto_id")
////    )
////    private List<Produto> produtos = new ArrayList<>();

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

    public Comercio() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
