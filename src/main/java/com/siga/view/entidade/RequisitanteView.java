/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.view.entidade;

import com.siga.model.Entidade;
import com.siga.model.Requisitante;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author franc
 */
public class RequisitanteView extends EntidadeView{
    
    @Override
    public void atualizarTabela(List<? extends Entidade> entidades){
       DefaultTableModel tableModel = (DefaultTableModel) this.getTabelaEntidade().getModel();
       
       tableModel.setRowCount(0);
       
       for (Entidade ent: entidades){
           if(ent instanceof Requisitante requisitante){
               
               tableModel.addRow(new Object[]{
                   requisitante.getId(),
                   requisitante.getNome(),
                   requisitante.getSetor(),
                   requisitante.getEndereco()
                    
                }
            );
           
        }
       
       
       }
    }
}
