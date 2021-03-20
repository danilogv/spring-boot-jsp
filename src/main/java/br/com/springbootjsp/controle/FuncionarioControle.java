package br.com.springbootjsp.controle;

import br.com.springbootjsp.modelo.servico.FuncionarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioControle {

    @Autowired
    private FuncionarioServico servico;

    @RequestMapping(value = "/funcionario", method = RequestMethod.GET)
    public ModelAndView listarFuncionarios() {
        ModelAndView visao = new ModelAndView("listar_funcionarios");
        return visao;
    }

}
