                                   
package com.siga.model;

//Herdando da superclasse Entidade
public class Requisitante extends Entidade{
    private String nome;
    private String setor;

    public Requisitante(){};
    
    public Requisitante(String nome, String setor) {
        this.nome = nome;
        this.setor = setor;
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
