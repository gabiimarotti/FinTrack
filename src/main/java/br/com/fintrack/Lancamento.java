package br.com.fintrack;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lancamento {
    private final String descricao;
    private final BigDecimal valor;
    private final LocalDate vencimento;
    private final TipoLancamento tipo;
   
    public Lancamento(String descricao, TipoLancamento tipo, BigDecimal valor, LocalDate vencimento) {

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }

        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }

        if (valor.scale() > 2) {
            throw new IllegalArgumentException(
                "O valor deve ter até duas casas decimais."
            );
        }

        if (vencimento == null) {
            throw new IllegalArgumentException("O vencimento é obrigatório.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo é obrigatório.");
        }

        this.tipo = tipo;
        this.descricao = descricao.trim();
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

}