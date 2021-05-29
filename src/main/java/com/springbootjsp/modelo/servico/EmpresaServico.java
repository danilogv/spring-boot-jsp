package com.springbootjsp.modelo.servico;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio repositorio;

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Empresa> listar() {
        List<Empresa> empresas = this.repositorio.buscarTodos();
        return empresas;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Empresa buscar(String id) {
        Empresa empresa = this.repositorio.buscar(id);
        return empresa;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void salvar(Empresa empresa) {
        String nome = empresa.getNome();
        String cnpj = empresa.getCnpj();
        if(this.repositorio.existe(cnpj) == 0) {
            String id = UUID.randomUUID().toString();
            this.repositorio.insere(id,nome,cnpj);
        }
        else {
            String id = empresa.getId();
            this.repositorio.altera(nome,cnpj,id);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
