<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresa </title>
    </head>
    <body>
        <form method="post" action="/empresa">
            <label> Nome : </label>
            <input type="text" name="nome" value="${empresa.nome}" />
            <br/> <br/>
            <label> CNPJ : </label>
            <input type="text" name="cnpj" value="${empresa.cnpj}" />
            <br/>
            <input type="hidden" name="id" value="${empresa.id}" />
            <button type="submit" style="position: absolute;top: 100px"> Salvar </button>
        </form>
        <a href="/empresas">
            <button style="position: absolute;top: 100px;left: 70px"> Cancelar </button>
        </a>
    </body>
</html>
