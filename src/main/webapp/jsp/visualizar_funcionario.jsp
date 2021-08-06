<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Funcionário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#"> Sys Contábil </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#barra-navegacao" aria-controls="barra-navegacao" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="barra-navegacao">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="/empresas"> Empresas </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/funcionarios"> Funcionários </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/relatorios"> Relatórios </a>
                    </li>
                    <li class="nav-item" style="position: absolute; right: 5px">
                        <a class="nav-link" href="/logout"> Sair </a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="mt-2 ml-2">
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
    </body>
</html>
