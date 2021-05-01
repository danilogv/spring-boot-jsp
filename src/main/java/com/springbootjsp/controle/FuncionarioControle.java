package com.springbootjsp.controle;

import com.springbootjsp.modelo.dominio.Funcionario;
import com.springbootjsp.modelo.servico.FuncionarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class FuncionarioControle {

    @Autowired
    private FuncionarioServico servico;

    @RequestMapping(value = "/funcionario", method = RequestMethod.GET)
    public ModelAndView listar(Model modelo) {
        Map<String,Funcionario> funcionarios = this.servico.listar();
        modelo.addAttribute("funcionarios", funcionarios);
        ModelAndView visao = new ModelAndView("listar_funcionarios");
        return visao;
    }

    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
    public ModelAndView buscar(@PathVariable String id, Model modelo) {
        Funcionario funcionario = this.servico.buscar(id);
        modelo.addAttribute("funcionario", funcionario);
        ModelAndView visao = new ModelAndView ("formulario_funcionario");
        return visao;
    }

    @RequestMapping(value = "/funcionario", method = RequestMethod.POST)
    public ModelAndView salvar(@RequestBody Funcionario funcionario) {
        this.servico.salvar(funcionario);
        ModelAndView visao = new ModelAndView ("formulario_funcionario");
        return visao;
    }

    @RequestMapping(value = "/funcionario", method = RequestMethod.PUT)
    public ModelAndView editar(@RequestBody Funcionario funcionario) {
        this.servico.salvar(funcionario);
        ModelAndView visao = new ModelAndView ("formulario_funcionario");
        return visao;
    }

    @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE)
    public ModelAndView excluir(@PathVariable String id) {
        this.servico.excluir(id);
        ModelAndView visao = new ModelAndView ("formulario_funcionario");
        return visao;
    }

}
