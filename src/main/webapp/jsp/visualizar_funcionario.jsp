<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width,initial-scale=1" />
        <title> Funcionário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/bootstrap.js"> </script>
    </head>
    <body>
        <c:import url="barra_navegacao.jsp" />
        <div class="my-2 mx-2">
            <h1> ${funcionario.nome} </h1>
            <br/>
            <p class="font-weight-light"> CPF : ${funcionario.cpf} </p>
            <br/>
            <p class="font-weight-light"> Salário : ${salario} </p>
            <br/>
            <p class="font-weight-light"> Idade : ${funcionario.idade} </p>
            <br/>
            <p class="font-weight-light"> Empresa : ${funcionario.empresa.nome} </p>
            <br/>
            <c:if test="${not empty dataDesligamento}">
                <p class="font-weight-light"> Data de desligamento : ${dataDesligamento} </p>
                <br/>
            </c:if>
            <a href="/funcionarios">
                <button type="button" class="btn btn-primary"> Voltar </button>
            </a>
        </div>
        <c:import url="rodape.jsp" />
    </body>
</html>
