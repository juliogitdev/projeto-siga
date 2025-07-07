package com.siga.model;

//Herdando da superclasse Entidade
public class Produto extends Entidade{
    private String nomeProduto;
    private String descricao;
    private int quantidade;
    private Fornecedor fornecedor;
    private Categoria categoria;

    public Produto() {
        super();
    }

    public Produto(String nomeProduto, String descricao, int quantidade, Fornecedor fornecedor, Categoria categoria) {
        super();
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }


    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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
    
    @Override
    public String toString(){
        return nomeProduto;
    }
    
}
