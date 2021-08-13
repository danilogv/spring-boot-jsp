package com.springbootjsp.controle;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RelatorioControle {

    @RequestMapping(value = "/relatorios",method = RequestMethod.GET)
    public ModelAndView relatorios() {
        ModelAndView visao = new ModelAndView("/relatorio");
        return visao;
    }

    @RequestMapping(value = "/relatorio",method = RequestMethod.GET)
    public ModelAndView relatorio(Model modelo,HttpServletResponse resposta) {
        ModelAndView visao = new ModelAndView("/relatorio");
        Resource recurso = new ClassPathResource("relatorio/empresa.jasper");
        Map<String, Object> parametros = new HashMap<>();
        resposta.setContentType("application/x-pdf");
        resposta.setHeader("Content-disposition", "inline;filename=empresas.pdf");
        try {
            InputStream entrada = recurso.getInputStream();
            JasperReport relatorio = (JasperReport) JRLoader.loadObject(entrada);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio,parametros,new JREmptyDataSource());
            OutputStream saida = resposta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(impressao,saida);
        }
        catch (IOException | JRException e) {
            modelo.addAttribute("mensagem", "Erro ao abrir o arquivo");
            e.printStackTrace();
        }
        return visao;
    }

}
