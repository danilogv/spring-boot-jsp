package com.springbootjsp.controle;

import com.springbootjsp.modelo.dominio.Funcionario;
import com.springbootjsp.modelo.servico.FuncionarioServico;
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
public class FuncionarioControle {

    @Autowired
    private FuncionarioServico servico;

    private final Integer QTD_POR_PAGINA = 3;

    private final Integer QTD_MAXIMA_PAGINAS = 3;

    @RequestMapping(value = {"/funcionarios","/funcionarios/{nome}"},method = RequestMethod.GET)
    public ModelAndView listar(@PathVariable(required = false) String nome,@RequestParam(defaultValue = "0") Integer pagina,Model modelo) {
        if (pagina < 0)
            pagina = 0;
        if (nome == null)
            nome = "";
        List<Funcionario> funcionarios = this.servico.listar(nome);
        PagedListHolder<Funcionario> funcionariosPaginacao = new PagedListHolder<>(funcionarios);
        funcionariosPaginacao.setPageSize(this.QTD_POR_PAGINA);
        funcionariosPaginacao.setPage(pagina);
        funcionarios = funcionariosPaginacao.getPageList();
        modelo.addAttribute("funcionarios",funcionarios);
        modelo.addAttribute("nome",nome);
        modelo.addAttribute("numero_paginas",funcionariosPaginacao.getPageCount());
        modelo.addAttribute("pagina_anterior",pagina - 1);
        if (pagina > this.QTD_MAXIMA_PAGINAS)
            modelo.addAttribute("pagina_atual",this.QTD_MAXIMA_PAGINAS);
        else
            modelo.addAttribute("pagina_atual",pagina);
        modelo.addAttribute("pagina_posterior",pagina + 1);
        modelo.addAttribute("qtd_maxima_paginas",this.QTD_MAXIMA_PAGINAS);
        ModelAndView visao = new ModelAndView("listar_funcionarios");
        return visao;
    }

    @RequestMapping(value = {"/funcionario/{id}","/funcionario/{opcao}/{id}"},method = RequestMethod.GET)
    public ModelAndView buscar(@PathVariable(required = false) String opcao,@PathVariable(required = false) String id,Model modelo) {
        ModelAndView visao = new ModelAndView();
        if (id != null) {
            Funcionario funcionario = this.servico.buscar(id);
            modelo.addAttribute("funcionario",funcionario);
            if (opcao.equals("editar"))
                visao.setViewName("formulario_funcionario");
            else
                visao.setViewName("visualizar_empresa");
        }
        else
            visao.setViewName("formulario_funcionario");
        return visao;
    }

    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public RedirectView salvar(@ModelAttribute Funcionario funcionario,RedirectAttributes atributos) {
        String mensagem = null;
        try {
            if (funcionario.getId() == null || funcionario.getId().isEmpty()) {
                this.servico.inserir(funcionario);
                mensagem = "Inserção feita com sucesso.";
            }
            else {
                this.servico.alterar(funcionario);
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

    @RequestMapping(value = "/empresa/{id}",method = RequestMethod.POST)
    public RedirectView excluir(@PathVariable String id,RedirectAttributes atributos) {
        String mensagem = null;
        try {
            this.servico.excluir(id);
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
