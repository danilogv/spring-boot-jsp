<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresa </title>
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />
        <script src="../js/jquery.js"> </script>
        <script src="../js/jquery.mask.js"> </script>
        <script src="../js/empresa.js"> </script>
    </head>
    <body>
        <form id="form_salvar" method="post" action="/empresa">
            <div class="container-fluid">
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
            </div>
            <br />
            <input type="hidden" name="id" value="${empresa.id}" />
            <div class="ml-3">
                <button type="submit" class="btn btn-primary"> Salvar </button>
                <a href="/empresas">
                    <button type="button" class="btn btn-primary"> Cancelar </button>
                </a>
            </div>
        </form>
    </body>
</html>
