package br.com.fintrack;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinTrack {

    public static void main(String[] args) {
        Lancamento lanche = new Lancamento("Lanche",TipoLancamento.DESPESA,new BigDecimal("25.00"), LocalDate.of(2026, 10, 15));

        System.out.println(lanche.getDescricao());
        System.out.println(lanche.getValor());
        System.out.println(lanche.getVencimento());
        System.out.println(lanche.getTipo());
    }
}