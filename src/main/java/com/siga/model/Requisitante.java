                                   
package com.siga.model;


public class Requisitante implements Entidade{
    private int id;
    private String nome;
    private String setor;

    public Requisitante(){};
    
    public Requisitante(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
    }
    
    @Override
    public int getId(){
        return id;
    }
    
    @Override
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
    
    
    
    
}
