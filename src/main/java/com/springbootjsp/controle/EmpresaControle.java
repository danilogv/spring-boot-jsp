package com.springbootjsp.controle;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.servico.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class EmpresaControle extends ObjetoControle {

    @Autowired
    private EmpresaServico empresaServico;

    @RequestMapping(value = {"/empresas","/empresas/{nome}"},method = RequestMethod.GET)
    public ModelAndView listar(@PathVariable(required = false) String nome,@RequestParam(defaultValue = "0") Integer pagina,Model modelo) {
        pagina = validaPagina(pagina);
        nome = validaNome(nome);
        List<Empresa> empresas = this.empresaServico.listar(nome);
        PagedListHolder<Empresa> empresasPaginacao = new PagedListHolder<>(empresas);
        empresasPaginacao.setPageSize(this.QTD_POR_PAGINA);
        empresasPaginacao.setPage(pagina);
        empresas = empresasPaginacao.getPageList();
        modelo.addAttribute("empresas",empresas);
        modelo.addAttribute("numero_paginas",empresasPaginacao.getPageCount());
        atribuicaoModelo(modelo,nome,pagina);
        ModelAndView visao = new ModelAndView("listar_empresas");
        return visao;
    }

    @RequestMapping(value = {"/empresa","/empresa/{opcao}/{id}"},method = RequestMethod.GET)
    public ModelAndView buscar(@PathVariable(required = false) String opcao,@PathVariable(required = false) String id,Model modelo) {
        ModelAndView visao = new ModelAndView();
        if (id != null) {
            Empresa empresa = this.empresaServico.buscar(id);
            modelo.addAttribute("empresa",empresa);
            if (opcao.equals("editar"))
                visao.setViewName("formulario_empresa");
            else
                visao.setViewName("visualizar_empresa");
        }
        else
            visao.setViewName("formulario_empresa");
        return visao;
    }

    @RequestMapping(value = "/empresa",method = RequestMethod.POST)
    public RedirectView salvar(@ModelAttribute Empresa empresa,RedirectAttributes atributos) {
        String mensagem = null;
        try {
            if (empresa.getId() == null || empresa.getId().isEmpty()) {
                this.empresaServico.inserir(empresa);
                mensagem = "Inserção feita com sucesso.";
            }
            else {
                this.empresaServico.alterar(empresa);
                mensagem = "Edição feita com sucesso.";
            }
            atributos.addFlashAttribute("mensagemSucesso",mensagem);
        }
        catch (ResponseStatusException excecao) {
            mensagem = excecao.getReason();
            atributos.addFlashAttribute("mensagemErro",mensagem);
        }
        catch (Exception excecao) {
            mensagem = "Erro de servidor.";
            atributos.addFlashAttribute("mensagemErro",mensagem);
        }
        RedirectView visao = new RedirectView("/empresas");
        return visao;
    }

    @RequestMapping(value = "/empresa/{id}",method = RequestMethod.POST)
    public RedirectView excluir(@PathVariable String id,RedirectAttributes atributos) {
        String mensagem = null;
        try {
            this.empresaServico.excluir(id);
            mensagem = "Exclusão feita com sucesso.";
            atributos.addFlashAttribute("mensagemSucesso",mensagem);
        }
        catch (ResponseStatusException excecao) {
            mensagem = excecao.getReason();
            atributos.addFlashAttribute("mensagemErro",mensagem);
        }
        catch (Exception excecao) {
            mensagem = "Erro de servidor.";
            atributos.addFlashAttribute("mensagemErro",mensagem);
        }
        RedirectView visao = new RedirectView("/empresas");
        return visao;
    }

}
