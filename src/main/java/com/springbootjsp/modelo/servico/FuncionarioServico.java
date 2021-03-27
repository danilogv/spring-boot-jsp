package com.springbootjsp.modelo.servico;

import com.springbootjsp.modelo.dominio.Funcionario;
import com.springbootjsp.modelo.repositorio.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class FuncionarioServico {

    @Autowired
    private FuncionarioRepositorio repositorio;

    public Map<UUID,Funcionario> listar() {
        Map<UUID,Funcionario> funcionarios = this.repositorio.buscarTodos();
        return funcionarios;
    }

    public Funcionario buscar(String id) {
        Funcionario funcionario = this.repositorio.buscar(id);
        return funcionario;
    }

    public void salvar(Funcionario funcionario) {
        String id = funcionario.getId().toString();
        String nome = funcionario.getNome();
        String cpf = funcionario.getCpf();
        BigDecimal salario = funcionario.getSalario();
        Integer idade = funcionario.getIdade();
        Date dataAdmissao = funcionario.getDataAdmissao();
        String empresaId = funcionario.getEmpresa().getId().toString();
        Boolean existe = this.repositorio.existe(cpf,empresaId);
        if (!existe) {
            this.repositorio.insere(nome,cpf,salario,idade,dataAdmissao,empresaId);
        }
        else {
            this.repositorio.altera(nome,cpf,salario,idade,dataAdmissao,empresaId,id);
        }
    }

    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
