package com.springbootjsp.modelo.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "empresa")
public final class Empresa implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto)
            return true;
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        Empresa empresa = (Empresa) objeto;
        if (this.id.equals(empresa.getId()) || this.cnpj.equals(empresa.getCnpj()))
            return true;
        return false;
    }

}
