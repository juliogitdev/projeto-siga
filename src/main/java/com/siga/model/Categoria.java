package com.siga.model;


public class Categoria {
    private int id_categoria;
    private String nome_categoria;
    private String descricao;

    public Categoria() {
    }

    
    public Categoria(int id_categoria, String nome_categoria, String descricao) {
        this.id_categoria = id_categoria;
        this.nome_categoria = nome_categoria;
        this.descricao = descricao;
    }
    

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
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
