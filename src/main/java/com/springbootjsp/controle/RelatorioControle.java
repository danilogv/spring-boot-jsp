package com.springbootjsp.controle;

import com.springbootjsp.modelo.Empresa;
import com.springbootjsp.servico.EmpresaServico;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RelatorioControle {

    @Autowired
    private EmpresaServico empresaServico;

    @RequestMapping(value = "/relatorios",method = RequestMethod.GET)
    public ModelAndView relatorios() {
        ModelAndView visao = new ModelAndView("relatorio");
        return visao;
    }

    @RequestMapping(value = "/relatorio",method = RequestMethod.GET)
    public RedirectView relatorio(@RequestParam("tipo_relatorio") String tipo,Model modelo,RedirectAttributes atributos,HttpServletResponse resposta) {
        List<Empresa> empresas = this.empresaServico.listar("");
        try {
            if (empresas == null || empresas.size() == 0) {
                String msg = "Não existem empresas cadastradas.";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
            }
            String titulo = "Relatório de Empresas";
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO",titulo);
            Resource recurso = new ClassPathResource("relatorio/empresa.jasper");
            resposta.setContentType("application/x-pdf");
            resposta.setHeader("Content-disposition", "inline;filename=empresas.pdf");
            InputStream entrada = recurso.getInputStream();
            JasperReport relatorio = (JasperReport) JRLoader.loadObject(entrada);
            JRBeanCollectionDataSource dados = new JRBeanCollectionDataSource(empresas,false);
            JasperPrint impressao = JasperFillManager.fillReport(relatorio,parametros,dados);
            OutputStream saida = resposta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(impressao,saida);
        }
        catch (IOException | JRException excecao) {
            modelo.addAttribute("mensagem", "Erro ao abrir o arquivo");
            excecao.printStackTrace();
        }
        catch (ResponseStatusException excecao) {
            atributos.addFlashAttribute("mensagem",excecao.getReason());
            RedirectView visao = new RedirectView("/relatorios");
            return visao;
        }
        return null;
    }

}
