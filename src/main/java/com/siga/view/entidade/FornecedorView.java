package com.siga.view.entidade;

/**
 *
 * @author Julio
 */

import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import java.util.List;
import javax.swing.table.DefaultTableModel;
public class FornecedorView extends PanelPrincipal{
    
    //Concretiza o contrato da panel principal, adicioando o metodo de atualizar tabela
    @Override
    
    //o "? extends Entidade" significa que pode ser qualquer coisa que herde de entidade
    public void atualizarTabela(List<? extends Entidade> entidades){
        
        //Pega o modelo da tabela
        DefaultTableModel tableModel = (DefaultTableModel) this.getTabelaEntidade().getModel();
        
        //Limpa a tabela, deixando 0 linhas
        tableModel.setRowCount(0);
        
        for(Entidade ent : entidades){
            //Verifica se o tipo de entidade foi instanciada de fornecedor
            if(ent instanceof Fornecedor){
                Fornecedor fornecedor = (Fornecedor) ent;
                
                //Adiciona cada fornecedor com os campos abaixo
                tableModel.addRow(new Object[]{
                    fornecedor.getRazaoSocial(),
                    fornecedor.getCnpj(),
                    fornecedor.getEmail(),
                    fornecedor.getTelefone(),
                    fornecedor.getEndereco()
            });
            }
        }
    }
    
}
