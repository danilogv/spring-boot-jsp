<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresas </title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/bootstrap.css" />
        <script src="http://localhost:8080/js/jquery.js"> </script>
        <script src="http://localhost:8080/js/empresa.js"> </script>
    </head>
    <body id="corpo">
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
            <c:if test="${not empty mensagemSucesso}">
                <div class="alert alert-success" role="alert"> ${mensagemSucesso} </div>
                <br/>
            </c:if>
            <c:if test="${not empty mensagemErro}">
                <div class="alert alert-danger" role="alert"> ${mensagemErro} </div>
                <br/>
            </c:if>
            <div class="ml-3">
                <label> Pesquisar por nome : </label>
                <div class="row">
                    <div class="col-5 col-sm-5">
                        <input type="text" id="nome" name="nome" value="${nome}" class="form-control" autofocus />
                    </div>
                </div>
                <br/> <br/>
                <h1> Empresas </h1>
                <div class="row">
                    <div class="col-12 col-sm-9">
                        <table class="table">
                            <c:forEach var="empresa" items="${empresas}">
                                <tr class="d-flex">
                                    <td class="col-9">
                                        <c:out value="${empresa.nome}" />
                                    </td>
                                    <td class="col-1">
                                        <a href="/empresa/${"visualizar"}/${empresa.id}">
                                            <img src="../imagens/eye.svg" alt="Visualizar" />
                                        </a>
                                    </td>
                                    <td class="col-1">
                                        <a href="/empresa/${"editar"}/${empresa.id}">
                                            <img src="../imagens/pencil.svg" alt="Editar" />
                                        </a>
                                    </td>
                                    <td class="col-1">
                                        <form id="form_exclusao" action="/empresa/${empresa.id}" method="post">
                                            <input type="image" src="../imagens/trash.svg" alt="Excluir" />
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <br/> <br/>
                <ul class="pagination">
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${pagina_anterior > 0}">
                                <a href="/empresas?pagina=${pagina_anterior}" class="page-link"> Anterior </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/empresas" class="page-link"> Anterior </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <c:choose>
                        <c:when test="${pagina_atual >= qtd_maxima_paginas - 1}">
                            <c:choose>
                                <c:when test="${pagina_atual + 1 == numero_paginas}">
                                    <c:forEach begin="${numero_paginas - qtd_maxima_paginas + 1}" end="${numero_paginas}" step="1" var="i">
                                        <c:choose>
                                            <c:when test="${i - 1 == pagina_atual}">
                                                <li class="page-item active">
                                                    <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach begin="${pagina_atual}" end="${qtd_maxima_paginas + pagina_atual - 1}" step="1" var="i">
                                        <c:if test="${i <= numero_paginas}">
                                            <c:choose>
                                                <c:when test="${i - 1 == pagina_atual}">
                                                    <li class="page-item active">
                                                        <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item">
                                                        <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="1" end="${qtd_maxima_paginas}" step="1" var="i">
                                <c:if test="${i <= numero_paginas}">
                                    <c:choose>
                                        <c:when test="${i - 1 == pagina_atual}">
                                            <li class="page-item active">
                                                <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a href="/empresas?pagina=${i-1}" class="page-link"> ${i} </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <li class="page-item">
                        <c:choose>
                            <c:when test="${pagina_posterior < numero_paginas}">
                                <a href="/empresas?pagina=${pagina_posterior}" class="page-link"> Próximo </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/empresas?pagina=${pagina_posterior - 1}" class="page-link"> Próximo </a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
                <br/> <br/>
                <a href="/empresa">
                    <button type="button" class="btn btn-primary"> Cadastrar </button>
                </a>
            </div>
        </div>
    </body>
</html>
