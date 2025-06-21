package com.siga.model;

//Herdando da superclasse Entidade
public class Fornecedor extends Entidade{
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String endereco;

    public Fornecedor() {
    }

    public Fornecedor(String razaoSocial, String cnpj, String telefone, String endereco) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razao_social) {
        this.razaoSocial = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
