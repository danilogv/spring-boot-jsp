package com.springbootjsp.modelo.dominio;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "funcionario")
public final class Funcionario {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "data_admissao")
    private Date dataAdmissao;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto)
            return true;
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        Funcionario funcionario = (Funcionario) objeto;
        if (this.cpf.equals(funcionario.getCpf()))
            return true;
        if (this.cpf.equals(funcionario.getCpf()) && this.empresa.getId().equals(funcionario.getEmpresa().getId()))
            return true;
        return false;
    }

}
