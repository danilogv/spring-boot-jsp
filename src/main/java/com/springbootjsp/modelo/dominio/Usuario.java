package com.springbootjsp.modelo.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto)
            return true;
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        Usuario usuario = (Usuario) objeto;
        if (this.id.equals(usuario.getId()))
            return true;
        if (this.login.equals(usuario.getLogin()) && this.senha.equals(usuario.getSenha()))
            return true;
        return false;
    }

}
