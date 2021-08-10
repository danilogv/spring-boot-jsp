$(document).on("submit","#form_salvar",function() {
    var relatorioEmpresaSelecionado = $("#empresa").is(":checked");
    var relatorioFuncionarioSelecionado = $("#funcionario").is(":checked");
    if (!relatorioEmpresaSelecionado && !relatorioFuncionarioSelecionado) {
        alert("Selecione um RELATÓRIO à gerar.");
        return false;
    }
    return true;
});
