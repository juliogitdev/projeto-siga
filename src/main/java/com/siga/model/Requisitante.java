                                   
package com.siga.model;

//Herdando da superclasse Entidade
public class Requisitante extends Entidade{
    private String nome;
    private String setor;
    private String endereco;

    public Requisitante(){super();};
    
    public Requisitante(String nome, String setor, String endereco) {
        super();
        this.nome = nome;
        this.setor = setor;
        this.endereco = endereco;
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
  
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public String toString(){
        return nome;
    }
    
    
}
