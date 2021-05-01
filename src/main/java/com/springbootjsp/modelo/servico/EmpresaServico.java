package com.springbootjsp.modelo.servico;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio repositorio;

    public Map<String,Empresa> listar() {
        Map<String,Empresa> empresas = this.repositorio.buscarTodos();
        return empresas;
    }

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

    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
