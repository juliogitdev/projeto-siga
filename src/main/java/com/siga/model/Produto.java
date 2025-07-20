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




    public Produto(Produto outro) {
        super(); // se necessário (se Produto estende outra classe)
        this.nomeProduto = outro.nomeProduto;
        this.descricao = outro.descricao;
        this.quantidade = outro.quantidade;
        this.fornecedor = outro.fornecedor; // atenção: ainda é a mesma referência!
        this.categoria = outro.categoria;
        this.setId(outro.getId());// idem
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
