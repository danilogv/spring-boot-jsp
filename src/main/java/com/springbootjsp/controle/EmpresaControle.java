package com.springbootjsp.controle;

import com.springbootjsp.modelo.dominio.Empresa;
import com.springbootjsp.modelo.servico.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmpresaControle {

    @Autowired
    private EmpresaServico servico;

    @RequestMapping(value = "/empresa", method = RequestMethod.GET)
    public ModelAndView formulario() {
        ModelAndView visao = new ModelAndView("formulario_empresa");
        return visao;
    }

    @RequestMapping(value = "/empresas", method = RequestMethod.GET)
    public ModelAndView listar(@RequestParam(defaultValue = "0") Integer pagina, Model modelo) {
        List<Empresa> empresas = this.servico.listar();
        PagedListHolder<Empresa> empresasPaginacao = new PagedListHolder<>(empresas);
        empresasPaginacao.setPageSize(2);
        empresasPaginacao.setPage(pagina);
        empresas = empresasPaginacao.getPageList();
        modelo.addAttribute("empresas",empresas);
        modelo.addAttribute("numero_paginas",empresasPaginacao.getPageCount());
        modelo.addAttribute("pagina_anterior",pagina - 1);
        modelo.addAttribute("pagina_atual",pagina);
        modelo.addAttribute("pagina_posterior",pagina + 1);
        modelo.addAttribute("qtd_maxima_paginas", 6);
        ModelAndView visao = new ModelAndView("listar_empresas");
        return visao;
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
    public ModelAndView buscar(@PathVariable String id, Model modelo) {
        Empresa empresa = this.servico.buscar(id);
        modelo.addAttribute("empresa",empresa);
        ModelAndView visao = new ModelAndView("formulario_empresa");
        return visao;
    }

    @RequestMapping(value = "/empresa", method = RequestMethod.POST)
    public ModelAndView salvar(@ModelAttribute Empresa empresa) {
        this.servico.salvar(empresa);
        ModelAndView visao = new ModelAndView("listar_empresas");
        return visao;
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.POST)
    public ModelAndView excluir(@PathVariable String id) {
        this.servico.excluir(id);
        ModelAndView visao = new ModelAndView ("listar_empresas");
        return visao;
    }

}
