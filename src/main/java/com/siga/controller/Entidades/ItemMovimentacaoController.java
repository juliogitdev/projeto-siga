package com.siga.controller.Entidades;

import com.siga.dao.InterfaceDao;
import com.siga.dao.MovimentacaoDao;
import com.siga.dao.ProdutoDao;
import com.siga.model.Produto;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogItemMovimentacao;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.MovimentacaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julio
 */
public class ItemMovimentacaoController{

    private DialogItemMovimentacao dialogItemMovimentacao;
    private ProdutoDao produtoDao;
    private MovimentacaoController movimentacaoController;
    private List<Produto> produtos;
    private List<Produto> produtosDaBusca;
    
    public ItemMovimentacaoController(MovimentacaoController movController) {
        this.dialogItemMovimentacao = new DialogItemMovimentacao();
        this.produtoDao = new ProdutoDao();
        this.movimentacaoController = movController;
        
        carregarListProduto();
        dialogItemMovimentacao.atualizarTabela(produtos);
        produtosDaBusca = produtos;
        
        dialogItemMovimentacao.buttonBuscarAddEventListener(new BuscarProdutoListener());
        dialogItemMovimentacao.buttonSalvarAddEventListener(new SalvarItemMovimentacao());
        dialogItemMovimentacao.buttonCancelarAddEventListener(new CancelarListener());
        
    }
    
    public void carregarListProduto(){
        try{
            produtos = produtoDao.listarTodos();
        }catch(SQLException ex){
            System.out.println("Falha ao buscar todos os produtos: " + ex);
        }
    }

    public void buscarProdutosPorNome (String nomeProduto){
        List<Produto> produtosEncontrados = new ArrayList<>();
        
        if(nomeProduto.isBlank()){
            produtosEncontrados = produtos;
        }else{
            for (Produto p : produtos) {
                if (p.getNomeProduto().toLowerCase().contains(nomeProduto)) {
                    produtosEncontrados.add(p);
                }
            }
        }
        
        
        produtosDaBusca = produtosEncontrados;
        dialogItemMovimentacao.atualizarTabela(produtosDaBusca);
        
    }
    
    public void setVisible(Boolean b){
        dialogItemMovimentacao.setVisible(b);
        if(!b){
            dialogItemMovimentacao.dispose();
        }
    }
    
    class BuscarProdutoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarProdutosPorNome(dialogItemMovimentacao.getProdutoPesquisar().toLowerCase());
        }
        
    }
    
    class SalvarItemMovimentacao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantidadeItemMovimentacao = dialogItemMovimentacao.getQuantidade();
            int rowSelected = dialogItemMovimentacao.getRowSelected();
            if(rowSelected < 0){
                dialogItemMovimentacao.showMessage("Nenhum produto selecionado!");
                return;
            }
            if(quantidadeItemMovimentacao <= 0){
                dialogItemMovimentacao.showMessage("Por favor, insira a quantidade!");
                return;
            }
            
            Produto produtoItemMovimentacao = produtosDaBusca.get(rowSelected);
            

            
            if(movimentacaoController.getTipoMovimentacao().equals("SAIDA") & produtoItemMovimentacao.getQuantidade() < quantidadeItemMovimentacao){
                dialogItemMovimentacao.showMessage("Quantidade insuficiente no estoque!");
                return;
            }

            System.out.println(movimentacaoController.jaExisteProdutoNaMovimentacao(produtoItemMovimentacao.getId()));
            
            if(movimentacaoController.jaExisteProdutoNaMovimentacao(produtoItemMovimentacao.getId())){
                dialogItemMovimentacao.showMessage("Este produto já foi inserido na movimentação!");
                return;
            }
            
            dialogItemMovimentacao.limparInputs();

            buscarProdutosPorNome("");
            movimentacaoController.getDialogEntidade().showMessage("Produto adicionado com sucesso");
            movimentacaoController.adicionarProduto(produtoItemMovimentacao, quantidadeItemMovimentacao);
        }
        
    }
    
    class CancelarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dialogItemMovimentacao.setVisible(false);
            dialogItemMovimentacao.dispose();
        }
        
    }
    
}
