package br.com.springbootjsp.controle;

import br.com.springbootjsp.modelo.servico.EmpresaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpresaControle {

    @Autowired
    private EmpresaServico servico;

    @RequestMapping(value = "/empresa", method = RequestMethod.GET)
    public ModelAndView listarEmpresas() {
        ModelAndView visao = new ModelAndView("listar_empresas");
        return visao;
    }

}
