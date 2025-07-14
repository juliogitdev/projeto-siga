
package com.siga.model;

//Herdando da superclasse Entidade
public class Usuario extends Entidade{
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
        super();
    }

    public Usuario(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    @Override
    public String toString(){
        return this.getNome();
    }
}
