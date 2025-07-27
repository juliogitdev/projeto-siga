/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.dto.classesAbstratas;

import com.siga.model.Entidade;
import com.siga.model.Produto;
import java.time.LocalDateTime;

/**
 *
 * @author Julio
 */
public abstract class RelatorioTipoMovimentacaoProduto {
    private LocalDateTime dataHora;
    private String tipo;
    private Produto produto;
    private Entidade Entidade;
    private int quantidade;

    public RelatorioTipoMovimentacaoProduto(LocalDateTime dataHora, String tipo, Produto produto, int quantidade) {
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public abstract Entidade getEntidade();
    public abstract void setEntidade(Entidade entidade);
    
}
