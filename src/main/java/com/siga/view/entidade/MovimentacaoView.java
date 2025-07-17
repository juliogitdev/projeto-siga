package com.siga.view.entidade;

import com.siga.model.Entidade;
import com.siga.model.Movimentacao;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class MovimentacaoView extends EntidadeView{
    
    @Override
    public void atualizarTabela(List<? extends Entidade> entidades){
        DefaultTableModel tableModel = (DefaultTableModel) this.getTabelaEntidade().getModel();
        
        tableModel.setRowCount(0);
        
        for(Entidade ent: entidades){
            if(ent instanceof Movimentacao){
                Movimentacao mov = (Movimentacao) ent;
                
                
                
                tableModel.addRow(new Object[]{
                    mov.getId(),
                    mov.getDataHora(),
                    mov.getTipo(),
                    mov.getUsuario(),
                    mov.getFornecedor(),
                    mov.getRequisitante()
                    
                });
                
            }
        }
        
    }
    
    
    
    
}
