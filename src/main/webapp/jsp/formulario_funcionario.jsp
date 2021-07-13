<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Funcionário </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/jquery.mask.js"> </script>
        <script src="http://localhost:8080/js/funcionario.js"> </script>
    </head>
    <body>
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
                        <input type="number" id="salario" name="salario" value="${funcionario.salario}" step="0.01" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-2">
                        <label> Idade : </label>
                        <input type="number" id="idade" name="idade" value="${funcionario.idade}" min="18" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-4">
                        <label> Data de desligamento : </label>
                        <input type="date" id="data_desligamento" name="data_desligamento" value="${funcionario.dataDesligamento}" class="form-control" />
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <label> Empresa : </label>
                        <select class="form-control">
                            <option selected> </option>
                            <c:forEach items="${empresas}" var="empresa">
                                <option value="${empresa.id}"> ${empresa.nome} </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>