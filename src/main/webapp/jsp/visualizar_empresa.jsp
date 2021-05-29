<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresa </title>
    </head>
    <body>
        <h1> ${empresa.nome} </h1>
        <p>
            <label> CNPJ : ${empresa.cnpj} </label>
        </p>
        <a href="/empresas">
            <button> Voltar </button>
        </a>
    </body>
</html>
