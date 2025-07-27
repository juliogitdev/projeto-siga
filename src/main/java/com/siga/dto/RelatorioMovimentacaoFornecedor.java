package com.siga.dto;

import com.siga.dto.classesAbstratas.RelatorioMovimentacaoEntidade;
import com.siga.model.Entidade;
import com.siga.model.Produto;
import com.siga.model.Fornecedor;

import java.time.LocalDateTime;

public class RelatorioMovimentacaoFornecedor extends RelatorioMovimentacaoEntidade{

    private Fornecedor fornecedor;

    public RelatorioMovimentacaoFornecedor(int id, String tipo, LocalDateTime dataHora, Produto produto, Fornecedor fornecedor){
        super(id, tipo, dataHora, produto);
        this.fornecedor = fornecedor;
    }


    public Fornecedor getEntidade() {
        return fornecedor;
    }


    public void setEntidade(Entidade entidade) {
        this.fornecedor = (Fornecedor) entidade;
    }

    
}
