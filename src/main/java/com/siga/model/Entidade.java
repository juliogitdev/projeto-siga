
package com.siga.model;

//Super classe Entidade
public abstract class Entidade{
    
    private int id;
    
    public Entidade(){
        this.id = -1;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
}
