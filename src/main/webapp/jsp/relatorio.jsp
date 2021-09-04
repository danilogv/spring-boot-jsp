<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width,initial-scale=1" />
        <title> Relatórios </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/bootstrap.js"> </script>
        <script src="http://localhost:8080/js/relatorio.js"> </script>
    </head>
    <body>
        <c:import url="barra_navegacao.jsp" />
        <div class="container-fluid mt-2">
            <c:if test="${not empty mensagem}">
                <div class="alert alert-danger" role="alert"> ${mensagem} </div>
            </c:if>
            <br/>
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
        <c:import url="rodape.jsp" />
    </body>
</html>
