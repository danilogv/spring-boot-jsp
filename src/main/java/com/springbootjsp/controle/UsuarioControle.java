package com.springbootjsp.controle;

import com.springbootjsp.modelo.dominio.Usuario;
import com.springbootjsp.modelo.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsuarioControle {

    @Autowired
    private UsuarioServico usuarioServico;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView inicio() {
        ModelAndView visao = new ModelAndView("index");
        return visao;
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.GET)
    public ModelAndView formulario() {
        ModelAndView visao = new ModelAndView("formulario_usuario");
        return visao;
    }

    @RequestMapping(value = "/usuario",method = RequestMethod.POST)
    public RedirectView cadastro(@ModelAttribute Usuario usuario,RedirectAttributes atributos) {
        String mensagem = null;
        RedirectView visao = new RedirectView();
        try {
            this.usuarioServico.inserir(usuario);
            mensagem = "Cadastro realizado com sucesso.";
            atributos.addFlashAttribute("mensagemSucesso",mensagem);
            visao.setUrl("/");
        }
        catch (Exception excecao) {
            if (excecao instanceof  ResponseStatusException)
                mensagem = ((ResponseStatusException) excecao).getReason();
            else
                mensagem = "Erro de servidor.";
            atributos.addFlashAttribute("mensagemErro",mensagem);
            atributos.addFlashAttribute("login",usuario.getLogin());
            visao.setUrl("/usuario");
        }
        return visao;
    }

}
