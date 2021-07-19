<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Funcionários </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/funcionario.js"> </script>
    </head>
    <body>
        <div class="container-fluid mt-2">
            <c:if test="${not empty mensagemSucesso}">
                <div class="alert alert-success" role="alert"> ${mensagemSucesso} </div>
                <br/>
            </c:if>
            <c:if test="${not empty mensagemErro}">
                <div class="alert alert-danger" role="alert"> ${mensagemErro} </div>
                <br/>
            </c:if>
            <div class="ml-3">
                <a href="/funcionario">
                    <button type="button" class="btn btn-primary"> Cadastrar </button>
                </a>
            </div>
        </div>
    </body>
</html>
