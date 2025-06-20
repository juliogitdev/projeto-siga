
package com.siga.model;

import java.time.LocalDateTime;


public class Movimentacao {
    private int id_movimentacao;
    private LocalDateTime data_hora;
    private String tipo;
    private int quantidade;
    private Produto produto;
    private Usuario usuario;
    private Entidade entidade;
    
    
    public Movimentacao(LocalDateTime data_hora, String tipo, int quantidade, Produto produto, Usuario usuario, Entidade entidade) {
        this.data_hora = data_hora;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
        this.entidade = entidade;
    }
    
     
    
    public Movimentacao() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId_movimentacao() {
        return id_movimentacao;
    }

    public void setId_movimentacao(int id_movimentacao) {
        this.id_movimentacao = id_movimentacao;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }
    
}


