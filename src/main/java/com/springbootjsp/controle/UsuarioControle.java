package com.springbootjsp.controle;

import com.springbootjsp.modelo.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioControle {

    @Autowired
    private UsuarioServico usuarioServico;

    @RequestMapping(value = {"/","/usuario"},method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView visao = new ModelAndView("login");
        return visao;
    }

}
