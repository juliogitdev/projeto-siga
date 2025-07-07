
package com.siga.model;

//Super classe Entidade

import java.util.Objects;

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
    
    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Entidade entidade = (Entidade) o;
        
        return Objects.equals(id, entidade.id);
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
