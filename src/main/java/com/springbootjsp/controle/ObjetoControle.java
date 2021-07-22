package com.springbootjsp.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ObjetoControle {

    protected final Integer QTD_POR_PAGINA = 10;
    protected final Integer QTD_MAXIMA_PAGINAS = 5;

    protected Integer validaPagina(Integer pagina) {
        if (pagina < 0)
            pagina = 0;
        return pagina;
    }

    protected String validaNome(String nome) {
        if (nome == null)
            nome = "";
        return nome;
    }

    protected void atribuicaoModelo(Model modelo,String nome,Integer pagina) {
        modelo.addAttribute("nome",nome);
        modelo.addAttribute("pagina_anterior",pagina - 1);
        if (pagina > this.QTD_MAXIMA_PAGINAS)
            modelo.addAttribute("pagina_atual",this.QTD_MAXIMA_PAGINAS);
        else
            modelo.addAttribute("pagina_atual",pagina);
        modelo.addAttribute("pagina_posterior",pagina + 1);
        modelo.addAttribute("qtd_maxima_paginas",this.QTD_MAXIMA_PAGINAS);
    }

}
