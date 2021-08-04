package com.springbootjsp.modelo.repositorio;

import com.springbootjsp.modelo.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {

    @Modifying
    @Query(value =
        "INSERT INTO usuario(id,login,senha) " +
        "VALUES (?1,?2,?3)"
    ,nativeQuery = true)
    void insere(String id,String login,String senha);

    @Modifying
    @Query(value =
        "UPDATE usuario " +
        "SET login = ?1,senha = ?2 " +
        "WHERE id = ?3"
    ,nativeQuery = true)
    void altera(String login,String senha,String id);

    @Modifying
    @Query(value =
        "DELETE FROM usuario " +
        "WHERE id = ?1"
    ,nativeQuery = true)
    void remove(String id);

    @Query(value =
        "SELECT * " +
        "FROM usuario " +
        "ORDER BY nome"
    ,nativeQuery = true)
    List<Usuario> buscarTodos();

    @Query(value =
        "SELECT * " +
        "FROM usuario " +
        "WHERE id = ?1 " +
        "OR login = ?2"
    ,nativeQuery = true)
    Usuario buscar(String id,String login);

    @Query(value =
        "SELECT COUNT(id) > 0 " +
        "FROM usuario " +
        "WHERE login = ?1 " +
        "AND senha = ?2"
    ,nativeQuery = true)
    Integer existeUsuario(String login,String senha);

    @Query(value =
        "SELECT COUNT(id) > 0 " +
        "FROM usuario " +
        "WHERE login = ?1"
    ,nativeQuery = true)
    Integer existeLogin(String login);

}
