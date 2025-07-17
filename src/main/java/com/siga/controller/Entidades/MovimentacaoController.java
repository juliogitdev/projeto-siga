package com.siga.controller.Entidades;

import com.siga.dao.FornecedorDao;
import com.siga.dao.MovimentacaoDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Fornecedor;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogMovimentacao;
import com.siga.view.entidade.MovimentacaoView;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Julio
 */
public class MovimentacaoController extends EntidadeController{
    
   public MovimentacaoController(){
    super(new MovimentacaoView(), new MovimentacaoDao(), new DialogMovimentacao());

    
    DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();

    
    dm.setController(this);

    super.getView().setColunasTabela(new String[]{
        "ID", "DATA HORA", "Tipo", "Usuario", "Fornecedor", "Requisitante"
    });
    super.getView().setLabelEntidade("Movimentação");
    
}


    @Override
    public void carregarDadosEntidade() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void salvarEntidadeListener(String acao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void adicionar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void atualizar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void carregarComboBox(String action) throws SQLException{
        FornecedorDao fd = new FornecedorDao();
        RequisitanteDao rd = new RequisitanteDao();
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
        List<Requisitante> requisitantes =rd.listarTodos();
        List<Fornecedor> fornecedores =fd.listarTodos();
        
        if(action.equals("SAIDA")){
            dm.carregarRequisitate(requisitantes);
            
        }else{
            dm.carregarFornecedor(fornecedores);
        
        }
        
        
    
    }
}
