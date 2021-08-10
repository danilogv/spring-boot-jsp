package com.springbootjsp.servico;

import com.springbootjsp.modelo.Usuario;
import com.springbootjsp.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public List<Usuario> listar() {
        List<Usuario> usuarios = this.repositorio.buscarTodos();
        return usuarios;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public Usuario buscar(String id,String login) {
        Usuario usuario = this.repositorio.buscar(id,login);
        return usuario;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public Boolean existeLogin(String login) {
        if (this.repositorio.existeLogin(login).equals(1))
            return true;
        return false;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public Boolean existeUsuario(String login,String senha) {
        if (this.repositorio.existeUsuario(login,senha).equals(1))
            return true;
        return false;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void inserir(Usuario usuario) {
        String id = UUID.randomUUID().toString();
        String login = usuario.getLogin();
        String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
        if (this.existeLogin(login)) {
            String msg = "Usuário já cadastrado.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.insere(id,login,senha);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void alterar(Usuario usuario) {
        String id = usuario.getId();
        String login = usuario.getLogin();
        String senha = new BCryptPasswordEncoder().encode(usuario.getSenha());
        if (!this.buscar(id,login).getLogin().equals(login) && this.existeLogin(login)) {
            String msg = "Usuário já cadastrado.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.altera(login,senha,id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
