package com.siga.dto.classesAbstratas;


import com.siga.model.Entidade;
import com.siga.model.Produto;
import com.siga.model.Requisitante;
import java.sql.Date;
import java.time.LocalDateTime;

public abstract class RelatorioMovimentacaoEntidade {
    private int id;
    private String tipo;
    private LocalDateTime dataHora;
    private Produto produto;

    public RelatorioMovimentacaoEntidade(int id, String tipo, LocalDateTime dataHora, Produto produto) {
        this.id = id;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.produto = produto;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }


    public Produto getProduto() {
        return produto;
    }

    
}
