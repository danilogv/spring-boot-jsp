<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresa </title>
    </head>
    <body>
        <form method="post" action="/empresa">
            <label> Nome : </label>
            <input type="text" name="nome" />
            <br/> <br/>
            <label> CNPJ : </label>
            <input type="text" name="cnpj" />
            <br/> <br/>
            <button type="submit"> Salvar </button>
        </form>
    </body>
</html>