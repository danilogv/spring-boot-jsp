<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand mx-1" href="#"> Sys Contábil </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#barra-navegacao" aria-controls="barra-navegacao" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="barra-navegacao">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link fw-bold" href="/empresas"> Empresas </a>
            </li>
            <li class="nav-item">
                <a class="nav-link fw-bold" href="/funcionarios"> Funcionários </a>
            </li>
            <li class="nav-item">
                <a class="nav-link fw-bold" href="/relatorios"> Relatórios </a>
            </li>
        </ul>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link fw-bold" href="/logout"> Sair </a>
            </li>
        </ul>
    </div>
</nav>
