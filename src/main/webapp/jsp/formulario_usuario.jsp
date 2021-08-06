<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Usuário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/usuario.js"> </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#"> Sys Contábil </a>
        </nav>
        <form id="form_salvar" method="post" action="/usuario">
            <div class="container-fluid mt-2">
                <c:if test="${not empty mensagemErro}">
                    <div class="alert alert-danger" role="alert"> ${mensagemErro} </div>
                    <br/>
                </c:if>
                <div class="row">
                    <div class="col-12 col-sm-4">
                        <label> Login : </label>
                        <input type="text" id="login" name="login" value="${login}" class="form-control" />
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-12 col-sm-4">
                        <label> Senha : </label>
                        <input type="password" id="senha" name="senha" min="5" max="15" class="form-control" />
                    </div>
                </div>
                <br />
                <button type="submit" class="btn btn-primary"> Cadastrar </button>
                <a href="/">
                    <button type="button" class="btn btn-primary"> Voltar </button>
                </a>
            </div>
        </form>
    </body>
</html>
