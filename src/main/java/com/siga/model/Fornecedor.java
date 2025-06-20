package com.siga.model;


public class Fornecedor {
    private int id_fornecedor;
    private String razao_social;
    private String cnpj;
    private String telefone;
    private String endereco;

    public Fornecedor() {
    }

    public Fornecedor(String razao_social, String cnpj, String telefone, String endereco) {
        this.razao_social = razao_social;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    
    

    public int getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
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
