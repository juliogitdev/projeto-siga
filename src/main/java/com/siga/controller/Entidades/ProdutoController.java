package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.dao.FornecedorDao;
import com.siga.dao.InterfaceDao;
import com.siga.dao.ProdutoDao;
import com.siga.model.Categoria;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogProduto;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.ProdutoView;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;

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
    
    public void carregarComboBoxsDialog() throws SQLException{
        CategoriaDao cd = new CategoriaDao();
        FornecedorDao fd = new FornecedorDao();
        
        List<Categoria> categorias = null;
        List<Fornecedor> fornecedores = null;
        
        try{
            categorias = cd.listarTodos();
            fornecedores = fd.listarTodos();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        DialogProduto dialogProduto = (DialogProduto) getDialogEntidade();
        dialogProduto.carregarComboBoxCategoria(categorias);
        dialogProduto.carregarComboBoxFornecedor(fornecedores);
    }

    @Override
    public void carregarDadosEntidade() throws SQLException {
        DialogProduto dialogProduto = (DialogProduto) getDialogEntidade();
        
        int idProduto = getIdSelected();
        
        Produto produto = (Produto) getEntidadeDao().buscarPorId(idProduto);
        Categoria c = produto.getCategoria();
        Fornecedor f = produto.getFornecedor();
        
        dialogProduto.setNome(produto.getNomeProduto());
        dialogProduto.setDescricao(produto.getDescricao());
        
        if(c != null){
            dialogProduto.setCheckBoxCategoriaSelected(true);
            JComboBox comboBoxCategoria = dialogProduto.getjComboBoxCategoria();
            
            
            System.out.println(c);
            comboBoxCategoria.setVisible(true);
            comboBoxCategoria.setSelectedItem(c);
        }
        
        if(f != null){
            dialogProduto.setCheckBoxFornecedorSelected(true);
            JComboBox comboBoxFornecedor = dialogProduto.getjComboBoxFornecedor();
            comboBoxFornecedor.setSelectedItem(f);
            comboBoxFornecedor.setVisible(true);
        }
        
        
        
        
        
        
    }

    @Override
    public void salvarEntidadeListener(String acao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void adicionar() throws SQLException {
        
    }

    @Override
    protected void atualizar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
