package com.springbootjsp.modelo.servico;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio repositorio;

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Empresa> listar(String nome) {
        List<Empresa> empresas = this.repositorio.buscarTodos(nome);
        return empresas;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Empresa buscar(String id) {
        Empresa empresa = this.repositorio.buscar(id);
        return empresa;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void inserir(Empresa empresa) {
        String id = UUID.randomUUID().toString();
        String nome = empresa.getNome();
        String cnpj = empresa.getCnpj();
        if (!this.repositorio.existe(cnpj).equals(0)) {
            String msg = "Empresa com CNPJ já cadastrado.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.insere(id,nome,cnpj);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void alterar(Empresa empresa) {
        String id = empresa.getId();
        String nome = empresa.getNome();
        String cnpj = empresa.getCnpj();
        if (this.buscar(id).getCnpj().compareTo(cnpj) != 0 && !this.repositorio.existe(cnpj).equals(0)) {
            String msg = "Empresa com CNPJ já cadastrado.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.altera(nome,cnpj,id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
