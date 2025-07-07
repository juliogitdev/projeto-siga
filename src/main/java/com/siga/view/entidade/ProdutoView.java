package com.siga.view.entidade;

/**
 *
 * @author Julio
 */

import com.siga.model.Categoria;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;
import java.util.List;
import javax.swing.table.DefaultTableModel;
public class ProdutoView extends EntidadeView{
    
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
            if(ent instanceof Produto){
                Produto p = (Produto) ent;
                
                Categoria c = p.getCategoria();
                Fornecedor f = p.getFornecedor();
                
                String cNome = (c != null) ? c.getNome_categoria() : "Sem categoria";
                String fNome = (f != null) ? f.getRazaoSocial() : "Sem fornecedor";
                
                //Adiciona cada fornecedor com os campos abaixo
                tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNomeProduto(),
                    p.getDescricao(),
                    p.getQuantidade(),
                    cNome,
                    fNome
            });
            }
        }
    }
    
}
