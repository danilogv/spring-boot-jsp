<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Funcionário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/jquery.mask.js"> </script>
        <script src="http://localhost:8080/js/funcionario.js"> </script>
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
        <form id="form_salvar" method="post" action="/funcionario">
            <div class="container-fluid mt-2">
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <label> Nome : </label>
                        <input type="text" id="nome" name="nome" value="${funcionario.nome}" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-4">
                        <label> CPF : </label>
                        <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" value="${funcionario.cpf}" class="form-control" />
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-12 col-sm-4">
                        <label> Salário : </label>
                        <input type="text" id="salario" name="salario" value="${salario}" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-2">
                        <label> Idade : </label>
                        <input type="number" id="idade" name="idade" value="${funcionario.idade}" min="18" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-4">
                        <label> Data de desligamento : </label>
                        <input type="date" id="data_desligamento" name="dataDesligamento" max="${data_maxima}" value="${funcionario.dataDesligamento}" class="form-control" />
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <label> Empresa : </label>
                        <select id="empresa" name="empresa" class="form-control">
                            <c:if test="${empty funcionario}">
                                <option value=""> Selecione ... </option>
                            </c:if>
                            <c:forEach items="${empresas}" var="empresa">
                                <c:choose>
                                    <c:when test="${not empty funcionario && funcionario.empresa.id == empresa.id}">
                                        <option value="${empresa.id}" selected> ${empresa.nome} </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${empresa.id}"> ${empresa.nome} </option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br/>
                <input type="hidden" name="id" value="${funcionario.id}" />
                <button type="submit" class="btn btn-primary"> Salvar </button>
                <a href="/funcionarios">
                    <button type="button" class="btn btn-primary"> Cancelar </button>
                </a>
            </div>
        </form>
        <div class="rodape fixed-bottom">
            <div class="texto-rodape">
                &copyCopyright por Danilo Gonçalves Vicente
            </div>
        </div>
    </body>
</html>
