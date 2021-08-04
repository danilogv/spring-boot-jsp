$(document).ready(function(){
    $(document).on("submit","#form_salvar",function() {
        var login = $("#username").val();
        var senha = $("#password").val();
        if (login === "") {
            alert("Informe o LOGIN.");
            return false;
        }
        if (senha === "") {
            alert("Informe a SENHA.");
            return false;
        }
        return true;
    });
});
