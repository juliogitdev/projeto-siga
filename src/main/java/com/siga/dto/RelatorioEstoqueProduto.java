package com.siga.dto;

import com.siga.model.Produto;

public class RelatorioEstoqueProduto {
    private Produto produto;
    private int quantidadeAtual;

    public RelatorioEstoqueProduto(Produto produto, int quantidadeAtual) {
        this.produto = produto;
        this.quantidadeAtual = quantidadeAtual;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }
}
