package com.siga.model;

//Herdando da superclasse Entidade
public class Categoria extends Entidade{
    private String nomeCategoria;
    private String descricao;

    public Categoria() {
    }

    
    public Categoria(String nomeCategoria, String descricao) {
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }
    

    public String getNome_categoria() {
        return nomeCategoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nomeCategoria = nome_categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
