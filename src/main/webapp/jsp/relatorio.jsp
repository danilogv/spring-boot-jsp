<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Relatórios </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/relatorio.js"> </script>
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
        <div class="container-fluid mt-2">
            <c:if test="${not empty mensagem}">
                <div class="alert alert-danger" role="alert"> ${mensagem} </div>
            </c:if>
            <form id="form_salvar" method="get" action="/relatorio">
                <div class="container-fluid mt-3">
                    <div class="row">
                        <div class="col-12 col-sm-12">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="tipo_relatorio" id="relatorio-empresa" value="relatorio-empresa">
                                <label class="form-check-label" for="relatorio-empresa">
                                    Relatório de Empresas
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-sm-4">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="tipo_relatorio" id="relatorio-funcionario" value="relatorio-funcionario">
                                <label class="form-check-label" for="relatorio-funcionario">
                                    Relatório de Funcionários Ativos por empresa
                                </label>
                            </div>
                        </div>
                        <div class="col-12 col-sm-4">
                            <select id="empresa" name="empresa" class="form-control">
                                <option value=""> Selecione ... </option>
                                <c:forEach items="${empresas}" var="empresa">
                                    <option value="${empresa.id}"> ${empresa.nome} </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br />
                    <button type="submit" class="btn btn-primary"> Gerar </button>
                </div>
            </form>
        </div>
        <div class="rodape fixed-bottom">
            <div class="texto-rodape">
                &copyCopyright por Danilo Gonçalves Vicente
            </div>
        </div>
    </body>
</html>
