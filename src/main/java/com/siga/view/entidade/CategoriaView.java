package com.siga.view.entidade;

import com.siga.model.Categoria;
import com.siga.model.Entidade;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio
 */
public class CategoriaView extends PanelPrincipal{
    
    @Override
    public void atualizarTabela(List<? extends Entidade> entidades){
        DefaultTableModel tableModel = (DefaultTableModel) this.getTabelaEntidade().getModel();
        tableModel.setRowCount(0);
                
        for(Entidade entidade: entidades){
            if(entidade instanceof Categoria){
                Categoria c = (Categoria) entidade;
                
                tableModel.addRow(new Object[]{
                    c.getNome_categoria(),
                    c.getDescricao()
                });
                
            }
        }
    }
    
}
