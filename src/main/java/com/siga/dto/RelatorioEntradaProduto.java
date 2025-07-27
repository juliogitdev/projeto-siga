package com.siga.dto;

import com.siga.dto.classesAbstratas.RelatorioTipoMovimentacaoProduto;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;
import java.time.LocalDateTime;

/**
 *
 * @author Julio
 */
public class RelatorioEntradaProduto extends RelatorioTipoMovimentacaoProduto{

    private Fornecedor fornecedor;
    
    public RelatorioEntradaProduto(LocalDateTime dataHora, String tipo, Produto produto, int quantidade) {
        super(dataHora, tipo, produto   , quantidade);
        this.fornecedor = fornecedor;
    }

    
    
    @Override
    public Entidade getEntidade() {
        return fornecedor;
    }

    @Override
    public void setEntidade(Entidade entidade) {
        this.fornecedor = (Fornecedor) entidade;
    }
   
    
    
    
}
