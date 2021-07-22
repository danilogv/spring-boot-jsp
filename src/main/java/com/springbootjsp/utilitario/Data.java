package com.springbootjsp.utilitario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Data {

    public static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatar(LocalDate data) {
        return data.format(formato);
    }

}
