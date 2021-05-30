$(document).ready(function(){
    $(document).on("submit","#form_exclusao",function() {
        var msg = "Deseja realmente excluir essa empresa?\nTodos funcionários vinculados também serão excluidos.";
        if (confirm(msg)) {
            return true;
        }
        return false;
    });
    $(document).on("keyup","#nome",function() {
        var nome = $("#nome").val();
        $.get("/empresas/" + nome, function(resposta) {
            var temp = $.parseHTML(resposta);
            $("#corpo").html(temp);
            event.preventDefault();
        });
    });
});