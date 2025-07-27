package com.siga.dto;

import com.siga.dto.classesAbstratas.RelatorioTipoMovimentacaoProduto;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;
import com.siga.model.Requisitante;
import java.time.LocalDateTime;

/**
 *
 * @author Julio
 */
public class RelatorioSaidaProduto extends RelatorioTipoMovimentacaoProduto{

    private Requisitante requisitante;
    
    public RelatorioSaidaProduto(LocalDateTime dataHora, String tipo, Produto produto, int quantidade, Requisitante requisitante) {
        super(dataHora, tipo, produto, quantidade);
        this.requisitante = requisitante;
    }

    
    
    @Override
    public Entidade getEntidade() {
        return requisitante;
    }

    @Override
    public void setEntidade(Entidade entidade) {
        this.requisitante = (Requisitante) entidade;
    }
   
    
    
    
}
