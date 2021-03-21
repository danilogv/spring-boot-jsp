package com.springbootjsp.modelo.repositorio;

import com.springbootjsp.modelo.dominio.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa,UUID> {

    @Modifying
    @Query(value =
        "INSERT INTO empresa(nome,cnpj) " +
        "VALUES (?1,?2)"
    ,nativeQuery = true)
    void insere(String nome,String cnpj);

    @Modifying
    @Query(value =
        "UPDATE empresa " +
        "SET nome = ?1,cnpj = ?2 " +
        "WHERE id = ?3"
    ,nativeQuery = true)
    void altera(String nome,String cnpj,String id);

    @Modifying
    @Query(value =
        "DELETE FROM empresa " +
        "WHERE id = ?1"
    ,nativeQuery = true)
    void remove(String id);

    @Query(value =
        "SELECT * " +
        "FROM empresa"
    ,nativeQuery = true)
    List<Empresa> buscarTodos();

    @Query(value =
        "SELECT * " +
        "FROM empresa " +
        "WHERE id = ?1"
    ,nativeQuery = true)
    Empresa buscar(String id);

    @Query(value =
        "SELECT COUNT(id) > 0 " +
        "FROM empresa " +
        "WHERE cnpj = ?1"
    ,nativeQuery = true)
    Boolean existe(String cnpj);

}
