package com.siga.model;

public class Produto {
    private int id_produto;
    private String nome_produto;
    private String descricao;
    private int quantidade;
    private Fornecedor fornecedor;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int id_produto, String nome_produto, String descricao, int quantidade, Fornecedor fornecedor, Categoria categoria) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
