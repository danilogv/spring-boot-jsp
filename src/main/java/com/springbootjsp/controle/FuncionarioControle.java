package com.springbootjsp.controle;

import com.springbootjsp.modelo.Empresa;
import com.springbootjsp.modelo.Funcionario;
import com.springbootjsp.servico.EmpresaServico;
import com.springbootjsp.servico.FuncionarioServico;
import com.springbootjsp.utilitario.Data;
import com.springbootjsp.utilitario.Moeda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FuncionarioControle extends ObjetoControle {

    @Autowired
    private FuncionarioServico funcionarioServico;

    @Autowired
    private EmpresaServico empresaServico;

    @RequestMapping(value = {"/funcionarios","/funcionarios/{nome}"},method = RequestMethod.GET)
    public ModelAndView listar(@PathVariable(required = false) String nome,@RequestParam(defaultValue = "0") Integer pagina,Model modelo) {
        pagina = validaPagina(pagina);
        nome = validaNome(nome);
        List<Funcionario> funcionarios = this.funcionarioServico.listar(nome);
        PagedListHolder<Funcionario> funcionariosPaginacao = new PagedListHolder<>(funcionarios);
        funcionariosPaginacao.setPageSize(this.QTD_POR_PAGINA);
        funcionariosPaginacao.setPage(pagina);
        funcionarios = funcionariosPaginacao.getPageList();
        modelo.addAttribute("funcionarios",funcionarios);
        modelo.addAttribute("numero_paginas",funcionariosPaginacao.getPageCount());
        atribuicaoModelo(modelo,nome,pagina);
        ModelAndView visao = new ModelAndView("listar_funcionarios");
        return visao;
    }

    @RequestMapping(value = {"/funcionario","/funcionario/{opcao}/{id}"},method = RequestMethod.GET)
    public ModelAndView buscar(@PathVariable(required = false) String opcao,@PathVariable(required = false) String id,Model modelo) {
        ModelAndView visao = new ModelAndView();
        List<Empresa> empresas = this.empresaServico.listar("");
        modelo.addAttribute("empresas",empresas);
        modelo.addAttribute("data_maxima",Data.formatarPadraoAmericano(LocalDate.now()));
        if (id != null) {
            Funcionario funcionario = this.funcionarioServico.buscar(id);
            modelo.addAttribute("funcionario",funcionario);
            modelo.addAttribute("salario",Moeda.formatar(funcionario.getSalario()));
            if (opcao.equals("editar"))
                visao.setViewName("formulario_funcionario");
            else {
                if (funcionario.getDataDesligamento() != null) {
                    modelo.addAttribute("dataDesligamento",Data.formatarPadraoBrasileiro(funcionario.getDataDesligamento()));
                }
                visao.setViewName("visualizar_funcionario");
            }
        }
        else
            visao.setViewName("formulario_funcionario");
        return visao;
    }

    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public RedirectView salvar(@ModelAttribute Funcionario funcionario,@RequestParam("empresa") String empresaId,RedirectAttributes atributos) {
        String mensagem = null;
        Empresa empresa = new Empresa();
        empresa.setId(empresaId);
        funcionario.setEmpresa(empresa);
        try {
            if (funcionario.getId() == null || funcionario.getId().isEmpty()) {
                this.funcionarioServico.inserir(funcionario);
                mensagem = "Inserção feita com sucesso.";
            }
            else {
                this.funcionarioServico.alterar(funcionario);
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
        RedirectView visao = new RedirectView("/funcionarios");
        return visao;
    }

    @RequestMapping(value = "/funcionario/{id}",method = RequestMethod.POST)
    public RedirectView excluir(@PathVariable String id,RedirectAttributes atributos) {
        String mensagem = null;
        try {
            this.funcionarioServico.excluir(id);
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
        RedirectView visao = new RedirectView("/funcionarios");
        return visao;
    }

}
