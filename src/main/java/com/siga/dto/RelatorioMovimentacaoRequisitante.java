package com.siga.dto;


import com.siga.dto.classesAbstratas.RelatorioMovimentacaoEntidade;
import com.siga.model.Entidade;
import com.siga.model.Produto;
import com.siga.model.Requisitante;
import java.time.LocalDateTime;

public class RelatorioMovimentacaoRequisitante extends RelatorioMovimentacaoEntidade{

    private Requisitante requisitante;
    
    public RelatorioMovimentacaoRequisitante(int id, String tipo, LocalDateTime dataHora, Produto produto, Requisitante requisitante){
        super(id, tipo, dataHora, produto);
        this.requisitante = requisitante;
        
    }
    
    
    public Requisitante getEntidade() {
        return requisitante;
    }

    public void setEntidade(Entidade entidade) {
        this.requisitante = (Requisitante) entidade;
    }
    
    
    
}
