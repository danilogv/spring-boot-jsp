<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width,initial-scale=1" />
        <title> Empresa </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/estilo.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/jquery.mask.js"> </script>
        <script src="http://localhost:8080/js/bootstrap.js"> </script>
        <script src="http://localhost:8080/js/empresa.js"> </script>
    </head>
    <body>
        <c:import url="barra_navegacao.jsp" />
        <br/>
        <form id="form_salvar" method="post" action="/empresa">
            <div class="container-fluid mt-2">
                <div class="row">
                    <div class="col-12 col-sm-6">
                        <label> Nome : </label>
                        <input type="text" id="nome" name="nome" value="${empresa.nome}" class="form-control" />
                    </div>
                    <div class="col-12 col-sm-4">
                        <label> CNPJ : </label>
                        <input type="text" id="cnpj" name="cnpj" placeholder="00.000.000/0000-00" value="${empresa.cnpj}" class="form-control" />
                    </div>
                </div>
                <br />
                <input type="hidden" name="id" value="${empresa.id}" />
                <button type="submit" class="btn btn-primary"> Salvar </button>
                <a href="/empresas">
                    <button type="button" class="btn btn-primary"> Cancelar </button>
                </a>
            </div>
        </form>
        <c:import url="rodape.jsp" />
    </body>
</html>
