$(document).ready(function(){
    $("#form_exclusao").submit(function() {
        var msg = "Deseja realmente excluir essa empresa?\nTodos funcionários vinculados também serão excluidos.";
        if (confirm(msg)) {
            return true;
        }
        return false;
    });
});