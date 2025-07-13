
package com.siga.model;

import java.time.LocalDateTime;
import java.util.List;

//Herdando da superclasse Entidade
public class Movimentacao extends Entidade{
    private LocalDateTime dataHora;
    private String tipo;
    private Usuario usuario;
    private List<ItemMovimentacao> itemMovimentacao;
    private Requisitante requisitante;
    private Fornecedor fornecedor;
    
    
    public Movimentacao(LocalDateTime dataHora, String tipo, Usuario usuario, Requisitante requisitante, Fornecedor fornecedor, List<ItemMovimentacao> itemMovimentacao) {
        super();
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.itemMovimentacao = itemMovimentacao;
        this.usuario = usuario;
        this.requisitante = requisitante;
        this.fornecedor = fornecedor;
    }
    
     
    
    public Movimentacao() {
        super();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setData_hora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<ItemMovimentacao> getItemMovimentacao() {
        return itemMovimentacao;
    }

    public void setItemMovimentacao(List<ItemMovimentacao> itemMovimentacao) {
        this.itemMovimentacao = itemMovimentacao;
    }

    

    
    
}


