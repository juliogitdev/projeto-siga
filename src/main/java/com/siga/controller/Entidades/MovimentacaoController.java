package com.siga.controller.Entidades;

import com.siga.dao.MovimentacaoDao;
import com.siga.view.cadastros.Dialog.DialogMovimentacao;
import com.siga.view.entidade.MovimentacaoView;
import java.sql.SQLException;

/**
 *
 * @author Julio
 */
public class MovimentacaoController extends EntidadeController{
    
    public MovimentacaoController(){
        super(new MovimentacaoView(), new MovimentacaoDao(), new DialogMovimentacao());
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
    
}
