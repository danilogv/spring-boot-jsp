$(document).ready(function(){
    $(document).on("submit","#form_exclusao",function() {
        var msg = "Deseja realmente excluir essa empresa?\nTodos funcionários vinculados também serão excluidos.";
        if (confirm(msg)) {
            return true;
        }
        return false;
    });
    $(document).on("submit","#form_salvar",function() {
        var nome = $("#nome").val();
        var cnpj = $("#cnpj").val();
        if (nome === "") {
            alert("Informe o NOME.");
            return false;
        }
        if (cnpj === "") {
            alert("Informe o CNPJ.");
            return false;
        }
        return true;
    });
    $(document).on("keyup","#nome",function() {
        var nome = $("#nome").val();
        $.get("/empresas/" + nome, function(resposta) {
            var pagina = $.parseHTML(resposta);
            $("#corpo").html(pagina);
            $("#nome").focus().val("").val(nome);
            event.preventDefault();
        });
    });
    $(document).on("keypress","#cnpj",function() {
        $("#cnpj").mask("99.999.999/9999-99");
    });
});
