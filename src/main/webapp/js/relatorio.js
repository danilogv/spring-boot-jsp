$(document).ready(function(){
    $('#empresa').attr("hidden",true);
    $(document).on("submit","#form_salvar",function() {
        var relatorioEmpresaSelecionado = $("#relatorio-empresa").is(":checked");
        var relatorioFuncionarioSelecionado = $("#relatorio-funcionario").is(":checked");
        var empresa = $("#empresa").val();
        if (!relatorioEmpresaSelecionado && !relatorioFuncionarioSelecionado) {
            alert("Selecione um RELATÓRIO à gerar.");
            return false;
        }
        if (relatorioFuncionarioSelecionado && empresa === "") {
            alert("Informe a EMPRESA.");
            return false;
        }
        return true;
    });
    $(document).on("click","#relatorio-empresa",function() {
        $('#empresa').attr("hidden",true);
    });
    $(document).on("click","#relatorio-funcionario",function() {
        $('#empresa').attr("hidden",false);
    });
});
