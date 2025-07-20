package com.siga.controller.Entidades;

import com.siga.dao.FornecedorDao;
import com.siga.dao.MovimentacaoDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Fornecedor;
import com.siga.model.ItemMovimentacao;
import com.siga.model.Movimentacao;
import com.siga.model.Produto;
import com.siga.model.Requisitante;
import com.siga.model.Usuario;
import com.siga.view.cadastros.Dialog.DialogMovimentacao;
import com.siga.view.entidade.MovimentacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class MovimentacaoController extends EntidadeController {

    private List<Produto> produtosMovimentacao;
    private ItemMovimentacaoController itemMovimentacaoController;
    private Usuario usuario;
    
    public MovimentacaoController(Usuario usuario) {
        super(new MovimentacaoView(), new MovimentacaoDao(), new DialogMovimentacao());
        this.produtosMovimentacao = new ArrayList<>();
        this.itemMovimentacaoController = new ItemMovimentacaoController(this);
        this.usuario = usuario;
        
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
        dm.addProdutoActionListener(new AddProdutoListener());
        dm.excluirProdutoActionListener(new RemoveProdutoListener());
        
        dm.setController(this);
        
        
        super.getView().setColunasTabela(new String[]{
            "ID", "DATA HORA", "Tipo", "Usuario", "Fornecedor", "Requisitante"
        });
        super.getView().setLabelEntidade("Movimentação");
        
        setDefaultTipoMovimentacao();
    }
    
    @Override
    public void carregarDadosEntidade() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void salvarEntidadeListener(String acao) {
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();

        if(produtosMovimentacao.size() <= 0){
            dm.showMessage("Por favor, insira pelo menos um produto para cadastrar movimentação!");
            return;
        }
        
        LocalDateTime dataHoraAt = LocalDateTime.now().withNano(0);
        
        String tipo = dm.getTipoMovimentacao();
        Requisitante requisitante = null;
        Fornecedor fornecedor = null;
        List<ItemMovimentacao> itemsMovimentacao = new ArrayList<>();
        
        if(tipo.equals("ENTRADA")){
            fornecedor = (Fornecedor) dm.getTerceiros();
        }else{
            requisitante = (Requisitante) dm.getTerceiros();
        }
        
        System.out.println("Produtos para movimentação");
        for(Produto p : produtosMovimentacao){
            ItemMovimentacao newItemMov = new ItemMovimentacao(p.getId(), p.getQuantidade());
            System.out.println(p.getNomeProduto() + " | " + p.getQuantidade());
            itemsMovimentacao.add(newItemMov);
        }
        
        Movimentacao newMov = new Movimentacao(Timestamp.valueOf(dataHoraAt), tipo, usuario, requisitante, fornecedor, itemsMovimentacao);
        
        MovimentacaoDao movDao = (MovimentacaoDao) getEntidadeDao();
        atualizarTabelaProdutos();
        
        try{
            boolean cadastrado = movDao.realizarMovimentacaoCompleta(newMov);
            if(cadastrado){
                getDialogEntidade().showMessage("Movimentação realizada com sucesso!");
                itemMovimentacaoController.setVisible(false);
                itemMovimentacaoController.carregarListProduto();
                listarEntidadesTabela();
                dm.setVisible(false);
                dm.limparTabela();
            }else{
                getDialogEntidade().showMessage("Falha ao realizar movimentação, tente novamente!");
            }
        } catch (Exception ex) {
            System.out.println("Falha ao tentar realizar movimentação");
        }
        
    }
    
    @Override
    protected void adicionar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    protected void atualizar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void carregarComboBox(String action) throws SQLException {
        FornecedorDao fd = new FornecedorDao();
        RequisitanteDao rd = new RequisitanteDao();
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
        List<Requisitante> requisitantes = rd.listarTodos();
        List<Fornecedor> fornecedores = fd.listarTodos();
        
        System.out.println(action);
        System.out.println(dm.getTipoMovimentacao());
                
        if (action.equals("SAIDA")) {
            dm.carregarRequisitante(requisitantes);
            
        } else {
            dm.carregarFornecedor(fornecedores);
            
        }
        
    }

    public void atualizarTabelaProdutos() {
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
        dm.atualizarTabela(produtosMovimentacao);
    }
    
    public void adicionarProduto(Produto p, int quantidadeMovimentacao) {
        
        Produto pNovo = new Produto(p);
        
        pNovo.setQuantidade(quantidadeMovimentacao);
        produtosMovimentacao.add(pNovo);
        atualizarTabelaProdutos();
    }
    
    public String getTipoMovimentacao() {
        DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
        return dm.getTipoMovimentacao();
    }
    
    public boolean jaExisteProdutoNaMovimentacao(int id) {
        System.out.println("Buscando pelo id: " + id);
        for (Produto p : produtosMovimentacao) {
            if (p.getId() == id) {
                System.out.println("passando pelo produto: " + p);
                return true;
            }
        }
        
        return false;
    }
    
    public void setDefaultTipoMovimentacao(){
        try{
            carregarComboBox("ENTRADA");
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void limparProdutosMovimentacao(){
        produtosMovimentacao.clear();
    }
    
    class AddProdutoListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            itemMovimentacaoController.setVisible(true);
        }
        
    }
    
    class RemoveProdutoListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogMovimentacao dm = (DialogMovimentacao) getDialogEntidade();
            int rowSelected = dm.getIdProdutoSelected();
            
            if(rowSelected < 0){
                dm.showMessage("Selecione um produto para excluir da movimentação");
                return;
            }
            produtosMovimentacao.remove(rowSelected);
            atualizarTabelaProdutos();
            
        }
        
    }
}
