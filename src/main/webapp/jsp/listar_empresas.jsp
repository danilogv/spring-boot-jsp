<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <title> Empresas </title>
    </head>
    <body>
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
                        <button> Visualizar </button>
                    </td>
                    <td>
                        <button> Editar </button>
                    </td>
                    <td>
                        <button> Excluir </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/> <br/>
        <table>
            <tr>
                <c:forEach begin="1" end="${qtd_maxima_paginas}" step="1" var="i">
                    <c:if test="${i <= numero_paginas}">
                        <td>
                            <a href="/empresas?pagina=${i-1}"> ${i} </a>
                        </td>
                    </c:if>
                </c:forEach>
            </tr>
        </table>
        <br/> <br/>
        <a href="/empresa">
            <button> Cadastrar </button>
        </a>
    </body>
</html>
