package com.springbootjsp.utilitario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Data {

    private static DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatoAmericano = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatarPadraoBrasileiro(LocalDate data) {
        return data.format(formatoBrasileiro);
    }

    public static String formatarPadraoAmericano(LocalDate data) {
        return data.format(formatoAmericano);
    }

}
