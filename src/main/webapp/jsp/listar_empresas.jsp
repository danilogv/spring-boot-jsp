<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresas </title>
        <link rel="stylesheet" href="../css/empresa.css" />
        <script src="../js/jquery.js"> </script>
        <script src="../js/empresa.js"> </script>
    </head>
    <body id="corpo">
        <c:if test="${not empty mensagemSucesso}">
            <div class="painel_mensagem painel_mensagem_erro">
                <label class="mensagem"> ${mensagemSucesso} </label>
            </div>
            <br/> <br/>
        </c:if>
        <c:if test="${not empty mensagemErro}">
            <div class="painel_mensagem painel_mensagem_erro">
                <label class="mensagem"> ${mensagemErro} </label>
            </div>
            <br/> <br/>
        </c:if>
        <label> Pesquisar por nome : </label>
        <input type="text" id="nome" name="nome" value="${nome}" autofocus="true" />
        <br/> <br/>
        <table border="1">
            <tr>
                <th colspan="4"> Empresas </th>
            </tr>
            <c:forEach var="empresa" items="${empresas}">
                <tr>
                    <td>
                        <c:out value="${empresa.nome}" />
                    </td>
                    <td>
                        <a href="/empresa/${"visualizar"}/${empresa.id}">
                            <button> Visualizar </button>
                        </a>
                    </td>
                    <td>
                        <a href="/empresa/${"editar"}/${empresa.id}">
                            <button> Editar </button>
                        </a>
                    </td>
                    <td>
                        <form id="form_exclusao" action="/empresa/${empresa.id}" method="post">
                            <button> Excluir </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/> <br/>
        <table>
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${pagina_anterior > 0}">
                            <a href="/empresas?pagina=${pagina_anterior}"> Anterior </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/empresas"> Anterior </a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:choose>
                    <c:when test="${pagina_atual >= qtd_maxima_paginas - 1}">
                        <c:choose>
                            <c:when test="${pagina_atual + 1 == numero_paginas}">
                                <c:forEach begin="${numero_paginas - qtd_maxima_paginas + 1}" end="${numero_paginas}" step="1" var="i">
                                    <td>
                                        <a href="/empresas?pagina=${i-1}"> ${i} </a>
                                    </td>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach begin="${pagina_atual}" end="${qtd_maxima_paginas + pagina_atual - 1}" step="1" var="i">
                                    <c:if test="${i <= numero_paginas}">
                                        <td>
                                            <a href="/empresas?pagina=${i-1}"> ${i} </a>
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="1" end="${qtd_maxima_paginas}" step="1" var="i">
                            <c:if test="${i <= numero_paginas}">
                                <td>
                                    <a href="/empresas?pagina=${i-1}"> ${i} </a>
                                </td>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <td>
                    <c:choose>
                        <c:when test="${pagina_posterior < numero_paginas}">
                            <a href="/empresas?pagina=${pagina_posterior}"> Próximo </a>
                        </c:when>
                        <c:otherwise>
                            <a href="/empresas?pagina=${pagina_posterior - 1}"> Próximo </a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
        <br/> <br/>
        <a href="/empresa">
            <button> Cadastrar </button>
        </a>
    </body>
</html>
