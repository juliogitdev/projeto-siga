package com.siga.dto;

public class RelatorioProdutoMaisMovimentado {
    private String nomeProduto;
    private int totalEntradas;
    private int totalSaidas;
    private int totalMovimentado;

    public RelatorioProdutoMaisMovimentado(String nomeProduto, int totalEntradas, int totalSaidas, int totalMovimentado) {
        this.nomeProduto = nomeProduto;
        this.totalEntradas = totalEntradas;
        this.totalSaidas = totalSaidas;
        this.totalMovimentado = totalMovimentado;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(int totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public int getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(int totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public int getTotalMovimentado() {
        return totalMovimentado;
    }

    public void setTotalMovimentado(int totalMovimentado) {
        this.totalMovimentado = totalMovimentado;
    }

    
}
