package com.springbootjsp.servico;

import com.springbootjsp.modelo.Funcionario;
import com.springbootjsp.repositorio.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioServico {

    @Autowired
    private FuncionarioRepositorio repositorio;

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public List<Funcionario> listar(String nome) {
        List<Funcionario> funcionarios = this.repositorio.buscarTodos(nome);
        return funcionarios;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    public Funcionario buscar(String id) {
        Funcionario funcionario = this.repositorio.buscar(id);
        return funcionario;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void inserir(Funcionario funcionario) {
        String id = UUID.randomUUID().toString();
        String nome = funcionario.getNome();
        String cpf = funcionario.getCpf();
        BigDecimal salario = funcionario.getSalario();
        Integer idade = funcionario.getIdade();
        LocalDate dataDesligamento = funcionario.getDataDesligamento();
        String empresaId = funcionario.getEmpresa().getId();
        if (this.repositorio.existe(cpf,empresaId).equals(1)) {
            String msg = "Funcionário já cadastrado na empresa.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.insere(id,nome,cpf,salario,idade,dataDesligamento,empresaId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void alterar(Funcionario funcionario) {
        String id = funcionario.getId();
        String nome = funcionario.getNome();
        String cpf = funcionario.getCpf();
        BigDecimal salario = funcionario.getSalario();
        Integer idade = funcionario.getIdade();
        LocalDate dataDesligamento = funcionario.getDataDesligamento();
        String empresaId = funcionario.getEmpresa().getId();
        if (!this.buscar(id).getCpf().equals(cpf) && this.repositorio.existe(cpf,empresaId).equals(1)) {
            String msg = "Funcionário já cadastrado na empresa.";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
        }
        this.repositorio.altera(nome,cpf,salario,idade,dataDesligamento,empresaId,id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void excluir(String id) {
        this.repositorio.remove(id);
    }

}
