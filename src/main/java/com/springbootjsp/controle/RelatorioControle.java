package com.springbootjsp.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RelatorioControle {

    @RequestMapping(value = "/relatorios",method = RequestMethod.GET)
    public ModelAndView relatorios() {
        ModelAndView visao = new ModelAndView("/relatorio");
        return visao;
    }

    @RequestMapping(value = "/relatorio",method = RequestMethod.GET)
    public ModelAndView relatorio() {
        ModelAndView visao = new ModelAndView("/teste");
        return visao;
    }

}
