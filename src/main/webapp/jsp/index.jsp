<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Usuário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/usuario.js"> </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#"> Sys Contábil </a>
        </nav>
        <br/>
        <form id="form_salvar" method="post" action="/login">
            <div class="container-fluid mt-2">
                <c:if test="${not empty mensagemSucesso}">
                    <div class="alert alert-success" role="alert"> ${mensagemSucesso} </div>
                    <br/>
                </c:if>
                <c:if test="${not empty mensagemErro}">
                    <div class="alert alert-danger" role="alert"> ${mensagemErro} </div>
                    <br/>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success" role="alert"> Sessão encerrada </div>
                    <br/>
                </c:if>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger" role="alert"> Usuário inválido </div>
                    <br/>
                </c:if>
                <div class="row">
                    <div class="col-12 col-sm-4">
                        <label> Login : </label>
                        <input type="text" id="username" name="username" class="form-control" />
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-12 col-sm-4">
                        <label> Senha : </label>
                        <input type="password" id="password" name="password" min="5" max="15" class="form-control" />
                    </div>
                </div>
                <br />
                <button type="submit" class="btn btn-primary"> Login </button>
                <br/> <br/>
                <a href="/usuario" class="link-primary"> Não é cadastrado? Então clique aqui e cadastre-se! </a>
            </div>
        </form>
        <div class="rodape fixed-bottom">
            <div class="texto-rodape">
                &copyCopyright por Danilo Gonçalves Vicente
            </div>
        </div>
    </body>
</html>
