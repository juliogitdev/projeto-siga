package com.siga.dto;

import com.siga.model.Requisitante;

public class TotalRequisicoesRequisitante {
    private Requisitante requisitante;
    private int totalRequisicoes;

    public TotalRequisicoesRequisitante(Requisitante requisitante, int totalRequisicoes) {
        this.requisitante = requisitante;
        this.totalRequisicoes = totalRequisicoes;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public int getTotalRequisicoes() {
        return totalRequisicoes;
    }

    public void setTotalRequisicoes(int totalRequisicoes) {
        this.totalRequisicoes = totalRequisicoes;
    }
}
