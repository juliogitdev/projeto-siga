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
import java.util.ArrayList;
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
        
        if(categorias != null){
            dialogProduto.carregarComboBoxCategoria(categorias);
        }
        
        if (fornecedores != null){
            dialogProduto.carregarComboBoxFornecedor(fornecedores);
        }
        
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
         try {
            if (acao.equals("Add")) {
                adicionar();
            } else if (acao.equals("Update")) {
                atualizar();
            }
            
            getDialogEntidade().setVisible(false);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    protected void adicionar() throws SQLException {
        ProdutoDao produtoDao = (ProdutoDao) getEntidadeDao();
        ProdutoView produtoView = (ProdutoView) getView();
        DialogProduto dialogProduto = (DialogProduto) getDialogEntidade();
        
        String nome, descricao;
        Categoria categoria = null;
        Fornecedor fornecedor = null;
        
        Produto produto = new Produto();
        
        nome = dialogProduto.getNome();
        descricao = dialogProduto.getDescricao();
        
        if(nome.isBlank()){
            dialogProduto.showMessage("Não deixe o nome do produto vazio");
        
        }
        
        if(dialogProduto.getjCheckBoxCategoria()){
            categoria = dialogProduto.getSelectedCategoria();
        }
        
        if(dialogProduto.getjCheckBoxFonecedor()){
            fornecedor = dialogProduto.getSelectedFornecedor();
        }
        
        produto.setNomeProduto(nome);
        produto.setDescricao(descricao);
        produto.setFornecedor(fornecedor);
        produto.setCategoria(categoria);
        
        produtoDao.cadastrar(produto);
        listarEntidadesTabela();
        dialogProduto.limparInputs();
        dialogProduto.showMessage("Produto Cadastrado com sucesso");
        
        
    }

    @Override
    protected void atualizar() throws SQLException {
        ProdutoDao produtoDao = (ProdutoDao) getEntidadeDao();
        ProdutoView produtoView = (ProdutoView) getView();
        DialogProduto dialogProduto = (DialogProduto) getDialogEntidade();
        
        int id = getIdSelected();
        
        Produto produto = produtoDao.buscarPorId(id);
        
        
        String nome, descricao;
        Categoria categoria = null;
        Fornecedor fornecedor = null;
        
        nome = dialogProduto.getNome();
        descricao = dialogProduto.getDescricao();
        
        if(nome.isBlank()){
            dialogProduto.showMessage("Não deixe o nome do produto vazio");
            return;
        
        }
        
        if(dialogProduto.getjCheckBoxCategoria()){
            categoria = dialogProduto.getSelectedCategoria();
        }
        
        if(dialogProduto.getjCheckBoxFonecedor()){
            fornecedor = dialogProduto.getSelectedFornecedor();
        }
        
        produto.setNomeProduto(nome);
        produto.setDescricao(descricao);
        produto.setFornecedor(fornecedor);
        produto.setCategoria(categoria);
        
        
        produtoDao.atualizar(produto);
        listarEntidadesTabela();
        dialogProduto.limparInputs();
        dialogProduto.setVisible(false);
        dialogProduto.showMessage("Produto atualizado com sucesso!");
        
        
        
    }
    
}
