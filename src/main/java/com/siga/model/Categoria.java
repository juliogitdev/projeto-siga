package com.siga.model;


public class Categoria implements Entidade{
    private int id;
    private String nome_categoria;
    private String descricao;

    public Categoria() {
    }

    
    public Categoria(String nome_categoria, String descricao) {
        this.nome_categoria = nome_categoria;
        this.descricao = descricao;
    }
    
    @Override
    public int getId(){
        return id;
    }
    
    @Override
    public void setId(int id){
        this.id = id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
