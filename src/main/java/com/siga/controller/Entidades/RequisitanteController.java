
package com.siga.controller.Entidades;

import com.siga.dao.RequisitanteDao;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogRequisitante;
import com.siga.view.entidade.RequisitanteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


public class RequisitanteController {
    private RequisitanteView requisitanteView;
    private RequisitanteDao requisitanteDao;
    private DialogRequisitante requisitanteDialog;
    private String[] colunasString = {"Nome", "Setor", "Endereço"};
    
    
    public RequisitanteController(){
        this.requisitanteView = new  RequisitanteView();
        this.requisitanteDao = new RequisitanteDao();
        this.requisitanteDialog = new DialogRequisitante();
        
        requisitanteView.setColunasTabela(colunasString);
        
        requisitanteView.setLabelEntidade("Requisitante");
        
        requisitanteView.addEntidadeListenner(new AddEntidade());
        requisitanteView.editarEntidadeListener(new EditarEntidade());
        requisitanteView.excluirEntidadeListener(new ExcluirEntidade());
    }
    
    public RequisitanteView getView(){
        return requisitanteView;
    }
    
    public void carregarRequisitantes() throws SQLException{
        List<Requisitante> requisitantes = requisitanteDao.listarTodos();
    
        requisitanteView.atualizarTabela(requisitantes);
    
    }
    
    //classe para manipular quando o botão de adicionar fornecedor é clicado
    class AddEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em adicionar");
        }
        
    }
    
     //classe para manipular quando o botão de editar fornecedor é clicado
    class EditarEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em editar");
        }   
    }
    //classe para manipular quando o botão de excluir fornecedor é clicado
    class ExcluirEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em excluir");
        }   
}
    
    
        
        
        
    
    
    
    }
    
    
    
    
    
    
    
    
