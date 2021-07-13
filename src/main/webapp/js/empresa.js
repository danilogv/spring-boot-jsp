function cnpjValido(cnpj) {
    cnpj = cnpj.replace(/[^\d]+/g,"");
    if (cnpj.length !== 14)
        return false;
    if (cnpj === "00000000000000")
        return false;
    if (cnpj === "11111111111111")
        return false;
    if (cnpj === "22222222222222")
        return false;
    if (cnpj === "33333333333333")
        return false;
    if (cnpj === "44444444444444")
        return false;
    if (cnpj === "55555555555555")
        return false;
    if (cnpj === "66666666666666")
        return false;
    if (cnpj === "77777777777777")
        return false;
    if (cnpj === "88888888888888")
        return false;
    if (cnpj === "99999999999999")
        return false;
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado !== parseInt(digitos.charAt(0)))
        return false;
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado !== parseInt(digitos.charAt(1)))
        return false;
    return true;
}

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
        if (!cnpjValido(cnpj)) {
            alert("CNPJ inválido.");
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
    $(document).on("keypress","#cnpj",function(event) {
        if (event.which !== 8 && event.which !== 0 && (event.which < 48 || event.which > 57))
            return false;
        $("#cnpj").mask("99.999.999/9999-99");
        return true;
    });
});
