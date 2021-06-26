<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresa </title>
        <link rel="stylesheet" href="../css/empresa.css" />
        <script src="../js/jquery.js"> </script>
        <script src="../js/jquery.mask.js"> </script>
        <script src="../js/empresa.js"> </script>
    </head>
    <body>
        <form method="post" action="/empresa">
            <label> Nome : </label>
            <input type="text" name="nome" value="${empresa.nome}" />
            <br/> <br/>
            <label> CNPJ : </label>
            <input type="text" id="cnpj" name="cnpj" value="${empresa.cnpj}" />
            <br/>
            <input type="hidden" name="id" value="${empresa.id}" />
            <button type="submit" class="botao"> Salvar </button>
        </form>
        <a href="/empresas">
            <button class="botao botao_cancelar"> Cancelar </button>
        </a>
    </body>
</html>
