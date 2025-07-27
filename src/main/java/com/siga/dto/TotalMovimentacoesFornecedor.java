package com.siga.dto;

public class TotalMovimentacoesFornecedor {
    private int idFornecedor;
    private String nomeFornecedor;
    private int totalMovimentacoes;

    public TotalMovimentacoesFornecedor(int idFornecedor, String nomeFornecedor, int totalMovimentacoes) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.totalMovimentacoes = totalMovimentacoes;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public int getTotalMovimentacoes() {
        return totalMovimentacoes;
    }
}
