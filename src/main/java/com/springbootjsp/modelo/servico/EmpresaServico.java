package com.springbootjsp.modelo.servico;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio repositorio;

    public Map<UUID,Empresa> listar() {
        Map<UUID,Empresa> empresas = this.repositorio.buscarTodos();
        return empresas;
    }

    public Empresa buscar(String id) {
        Empresa empresa = repositorio.buscar(id);
        return empresa;
    }

    public void salvar(Empresa empresa) {
        String id = empresa.getId().toString();
        String nome = empresa.getNome();
        String cnpj = empresa.getCnpj();
        Boolean existe = this.repositorio.existe(cnpj);
        if(!existe) {
            this.repositorio.insere(nome, cnpj);
        }
        else {
            this.repositorio.altera(nome,cnpj,id);
        }
    }

    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
