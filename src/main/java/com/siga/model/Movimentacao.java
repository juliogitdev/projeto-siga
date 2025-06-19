
package com.siga.model;


public class Movimentacao {
    int id_movimentacao;
    String data_hora;
    String tipo;
    int quantidade;
    Produto produto;
    Usuario usuario;
    Entidade entidade;
    
    
    public Movimentacao(String data_hora, String tipo, int quantidade, Produto produto, Usuario usuario) {
        this.data_hora = data_hora;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
        this.entidade = entidade;
    }

    public Movimentacao() {
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
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
    
    
    
}


