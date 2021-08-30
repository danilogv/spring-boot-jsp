package com.springbootjsp.controle;

import com.springbootjsp.modelo.Empresa;
import com.springbootjsp.modelo.Funcionario;
import com.springbootjsp.servico.EmpresaServico;
import com.springbootjsp.servico.FuncionarioServico;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RelatorioControle {

    @Autowired
    private EmpresaServico empresaServico;

    @Autowired
    private FuncionarioServico funcionarioServico;

    @RequestMapping(value = "/relatorios",method = RequestMethod.GET)
    public ModelAndView relatorios() {
        ModelAndView visao = new ModelAndView("relatorio");
        return visao;
    }

    @RequestMapping(value = "/relatorio",method = RequestMethod.GET)
    public RedirectView relatorio(@RequestParam("tipo_relatorio") String tipo,@RequestParam("empresa") String empresaId,RedirectAttributes atributos,HttpServletResponse resposta) {
        String titulo,pasta;
        try {
            switch(tipo) {
                case "empresa":
                    List<Empresa> empresas = this.empresaServico.listar("");
                    if (empresas == null || empresas.size() == 0) {
                        String msg = "Não existem empresas cadastradas.";
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
                    }
                    titulo = "Relatório de Empresas";
                    pasta = "relatorio/empresa.jasper";
                    this.gerarRelatorio(titulo,pasta,resposta,empresas,null);
                    break;
                case "funcionario":
                    List<Funcionario> funcionarios = this.funcionarioServico.listar("");
                    List<Funcionario> funcionariosAtivos = new ArrayList<>();
                    for (Funcionario funcionario : funcionarios) {
                        if (funcionario.getEmpresa().getId().equals(empresaId) && funcionario.getDataDesligamento() == null) {
                            funcionariosAtivos.add(funcionario);
                        }
                    }
                    if (funcionariosAtivos.size() == 0) {
                        String msg = "Não existem funcionários dessa empresa cadastrados.";
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,msg);
                    }
                    titulo = "Relatório de Funcionários";
                    pasta = "relatorio/funcionario.jasper";
                    this.gerarRelatorio(titulo,pasta,resposta,null,funcionariosAtivos);
                    break;
            }
        }
        catch (Exception excecao) {
            if (excecao instanceof ResponseStatusException) {
                atributos.addFlashAttribute("mensagem",((ResponseStatusException) excecao).getReason());
            }
            else {
                atributos.addFlashAttribute("mensagem","Erro ao abrir o arquivo");
            }
            RedirectView visao = new RedirectView("/relatorios");
            return visao;
        }
        return null;
    }

    private void gerarRelatorio(String titulo,String pasta,HttpServletResponse resposta,List<Empresa> empresas,List<Funcionario> funcionarios) throws JRException, IOException {
        resposta.setContentType("application/x-pdf");
        if (empresas != null)
            resposta.setHeader("Content-disposition", "inline;filename=empresas.pdf");
        else if(funcionarios != null) {
            resposta.setHeader("Content-disposition", "inline;filename=funcionarios.pdf");
        }
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TITULO",titulo);
        Resource recurso = new ClassPathResource(pasta);
        InputStream entrada = recurso.getInputStream();
        JasperReport relatorio = (JasperReport) JRLoader.loadObject(entrada);
        JRBeanCollectionDataSource dados = null;
        if (empresas != null)
            dados = new JRBeanCollectionDataSource(empresas,false);
        else if (funcionarios != null)
            dados = new JRBeanCollectionDataSource(funcionarios,false);
        JasperPrint impressao = JasperFillManager.fillReport(relatorio,parametros,dados);
        OutputStream saida = resposta.getOutputStream();
        JasperExportManager.exportReportToPdfStream(impressao,saida);
    }

}
