package com.siga.controller.Entidades;

import com.siga.dao.InterfaceDao;
import com.siga.dao.ProdutoDao;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogProduto;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.ProdutoView;
import java.sql.SQLException;

/**
 *
 * @author Julio
 */
public class ProdutoController extends EntidadeController{

    public ProdutoController() {
        super(new ProdutoView(), new ProdutoDao(), new DialogProduto());
        
        this.setColunasTabela(new String[]{"ID", "Produto", "Descrição", "Quantidade", "Categoria", "Fornecedor"});
        getView().setLabelEntidade("Produto");
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
