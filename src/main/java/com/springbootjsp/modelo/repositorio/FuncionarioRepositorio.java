package com.springbootjsp.modelo.repositorio;

import com.springbootjsp.modelo.dominio.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario,String> {

    @Modifying
    @Query(value =
        "INSERT INTO funcionario(id,nome,cpf,salario,idade,data_admissao,empresa_id) " +
        "VALUES (?1,?2,?3,?4,?5,?6,?7)"
    ,nativeQuery = true)
    void insere(String id,String nome,String cpf,BigDecimal salario,Integer idade,Date dataAdmissao,String empresaId);

    @Modifying
    @Query(value =
        "UPDATE funcionario " +
        "SET nome = ?1,cpf = ?2,salario = ?3,idade = ?4,data_admissao = ?5,empresa_id = ?6 " +
        "WHERE id = ?7"
    ,nativeQuery = true)
    void altera(String nome,String cpf,BigDecimal salario,Integer idade,Date dataAdmissao,String empresaId,String id);

    @Modifying
    @Query(value =
        "DELETE FROM funcionario " +
        "WHERE id = ?1"
    ,nativeQuery = true)
    void remove(String id);

    @Query(value =
        "SELECT * " +
        "FROM funcionario " +
        "WHERE nome LIKE %?1% " +
        "ORDER BY nome"
    ,nativeQuery = true)
    List<Funcionario> buscarTodos(String nome);

    @Query(value =
        "SELECT * " +
        "FROM funcionario " +
        "WHERE id = ?1"
    ,nativeQuery = true)
    Funcionario buscar(String id);

    @Query(value =
        "SELECT COUNT(id) > 0 " +
        "FROM funcionario " +
        "WHERE cpf = ?1 " +
        "AND empresa_id = ?2"
    ,nativeQuery = true)
    Integer existe(String cpf,String empresaId);

}
