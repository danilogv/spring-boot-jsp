package com.springbootjsp.utilitario;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class Moeda {

    private static DecimalFormat formato = new DecimalFormat("#,###.00");

    public static String formatar(BigDecimal valor) {
        return formato.format(valor);
    }

}
