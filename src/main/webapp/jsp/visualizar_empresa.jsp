<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width,initial-scale=1" />
        <title> Empresa </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/bootstrap.js"> </script>
    </head>
    <body>
        <c:import url="barra_navegacao.jsp" />
        <div class="my-2 mx-2">
            <h1> ${empresa.nome} </h1>
            <br/>
            <p class="font-weight-light"> CNPJ : ${empresa.cnpj} </p>
            <br/>
            <a href="/empresas">
                <button type="button" class="btn btn-primary"> Voltar </button>
            </a>
        </div>
        <c:import url="rodape.jsp" />
    </body>
</html>
