/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.dao;

import com.siga.model.ItemMovimentacao;
import com.siga.model.Movimentacao;
import com.siga.model.Produto;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author franc
 */


public class ItemMovimentacaoDao{


    
  
    /**private void validar(ItemMovimentacao itemMovimentacao, PreparedStatement pstm) throws SQLException{
        int idMovimentacao = itemMovimentacao.getMovimentacao().getId();
        int idProduto = itemMovimentacao.getProduto().getId();
        int quantidade = itemMovimentacao.getQuantidade();
        
        pstm.setInt(1, idMovimentacao);
        pstm.setInt(2, idProduto);
        pstm.setInt(3, quantidade);

        pstm.execute();
    }**/
    
    public void cadastrar(Connection conn, ItemMovimentacao im) throws SQLException{
        String sql = "INSERT INTO item_movimentacao(id_movimentacao, id_produto, quantidade) VALUES (?, ?, ?);";
        
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, im.getIdMovimentacao());
            pstm.setInt(2, im.getIdProduto());
            pstm.setInt(3, im.getQuantidade());
            
            int linhasAfetadas = pstm.executeUpdate();
            if(linhasAfetadas == 0){
                throw new SQLException("Ciação de item movimentação falhou, nenhuma linha afetada");
            }
        }
        
    }
    

    /**
    public List<ItemMovimentacao> listarTodos() throws SQLException {
        
        String sql = "SELECT * FROM item_movimentacao";
        List<ItemMovimentacao> listaItemMovimentacao = new ArrayList<>();
        
        ProdutoDao produtodao = new ProdutoDao();
        MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
        
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                
                ItemMovimentacao itM = new ItemMovimentacao();
                Produto produto = produtodao.buscarPorId(rs.getInt("id_produto"));
                Movimentacao movimentacao = movimentacaoDao.buscarPorId(rs.getInt("id_movimentacao"));
                
                itM.setProduto(produto);      
                itM.setMovimentacao(movimentacao);
                itM.setQuantidade(rs.getInt("quantidade"));
                listaItemMovimentacao.add(itM);
            }
        }
        
        return listaItemMovimentacao;
    }

    
    public Object buscarPorId(int id) throws SQLException {
       String sql = "SELECT * FROM item_movimentacao WHERE id_item_movimentacao = ?;";
       
       ItemMovimentacao itM = null;
       
       ProdutoDao produtodao = new ProdutoDao();
       MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
       
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                itM = new ItemMovimentacao();
                Produto produto = produtodao.buscarPorId(rs.getInt("id_produto"));
                Movimentacao movimentacao = movimentacaoDao.buscarPorId(rs.getInt("id_movimentacao"));
                
                itM.setProduto(produto);      
                itM.setMovimentacao(movimentacao);
                itM.setQuantidade(rs.getInt("quantidade"));
                
            }
        }
        return itM;
    }
**/
}
        
        
    
    
   
    



