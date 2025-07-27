package com.siga.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Julio
 */
public class HistoricoMovimentacaoProduto {
    private LocalDateTime dataHora;
    private String tipoMovimentacao; // Entrada ou Saída
    private int quantidade;
    private String nomeEntidade; // Fornecedor ou Requisitante
    private String tipoEntidade; // "Fornecedor" ou "Requisitante"

    public HistoricoMovimentacaoProduto(LocalDateTime dataHora, String tipoMovimentacao, int quantidade, String nomeEntidade, String tipoEntidade) {
        this.dataHora = dataHora;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.nomeEntidade = nomeEntidade;
        this.tipoEntidade = tipoEntidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getTipoEntidade() {
        return tipoEntidade;
    }

    public void setTipoEntidade(String tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }

    
}
