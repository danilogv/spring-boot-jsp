function cpfValido(cpf) {
    cpf = cpf.replace(/[^\d]+/g,'');
    if (cpf.length !== 11)
        return false;
    if (cpf === "00000000000")
        return false;
    if (cpf === "11111111111")
        return false;
    if (cpf === "22222222222")
        return false;
    if (cpf === "33333333333")
        return false;
    if (cpf === "44444444444")
        return false;
    if (cpf === "55555555555")
        return false;
    if (cpf === "66666666666")
        return false;
    if (cpf === "77777777777")
        return false;
    if (cpf === "88888888888")
        return false;
    if (cpf === "99999999999")
        return false;
    var soma = 0;
    for (var i = 0;i < 9;i++)
        soma += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (soma % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(9)))
        return false;
    soma = 0;
    for (var i = 0;i < 10;i++)
        soma += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (soma % 11);
    if (rev === 10 || rev === 11)
        rev = 0;
    if (rev !== parseInt(cpf.charAt(10)))
        return false;
    return true;
}

$(document).ready(function(){
    $(document).on("submit","#form_salvar",function() {
        var nome = $("#nome").val();
        var cpf = $("#cpf").val();
        var salario = $("#salario").val();
        var idade = $("#idade").val();
        var empresa = $("#empresa").val();
        if (nome === "") {
            alert("Informe o NOME.");
            return false;
        }
        if (cpf === "") {
            alert("Informe o CPF.");
            return false;
        }
        if (salario === "") {
            alert("Informe o SALÁRIO.");
            return false;
        }
        if (idade === "") {
            alert("Informe a IDADE.");
            return false;
        }
        if (empresa === "") {
            alert("Informe a EMPRESA.");
            return false;
        }
        if (!cpfValido(cpf)) {
            alert("CPF inválido.");
            return false;
        }
        salario = salario.replace(".","").trim();
        salario = salario.replace(",",".").trim();
        $("#salario").val(parseFloat(salario));
        return true;
    });
    $(document).on("keypress","#salario",function() {
        $("#salario").mask("#.##0,00",{reverse: true});
    });
    $(document).on("keypress","#cpf",function(event) {
        if (event.which !== 8 && event.which !== 0 && (event.which < 48 || event.which > 57))
            return false;
        $("#cpf").mask("999.999.999-99");
        return true;
    });
});
