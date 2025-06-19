                                   
package com.siga.model;


public class Requisitante extends Entidade{
    private int id;
    private String setor;
    
   

    public Requisitante(String nome, String setor) {
        super(nome);
        this.setor = setor;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
    
    
    
    
}
