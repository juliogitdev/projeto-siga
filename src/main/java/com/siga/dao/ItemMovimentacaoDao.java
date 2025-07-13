/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.dao;

import com.siga.model.ItemMovimentacao;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author franc
 */


public class ItemMovimentacaoDao implements InterfaceDao{

   //tive que criar assim pois não consegui sobre escrever o outro(cadastrar) com o item movimentacao como parametro dá uma olhada depois
    public void add(ItemMovimentacao itemMovimentacao) throws SQLException {
        String sql = "INSERT INTO item_movimentacao(id_movimentacao, id_produto, quantidade) VALUES (?, ?, ?);";
        
        try(Connection conn = ConnectionFactory.getConnection()){
            
            conn.setAutoCommit(false);
            
            try(PreparedStatement pstm = conn.prepareStatement(sql))
            { 
               validar(itemMovimentacao, pstm);
               conn.commit();
               
            }catch (Exception e){
                
                conn.rollback();
                System.out.println(e);
            }

            }
        
        }
    
  
    private void validar(ItemMovimentacao itemMovimentacao, PreparedStatement pstm) throws SQLException{
        int idMovimentacao = itemMovimentacao.getMovimentacao().getId();
        int idProduto = itemMovimentacao.getProduto().getId();
        int quantidade = itemMovimentacao.getQuantidade();
        
        pstm.setInt(1, idMovimentacao);
        pstm.setInt(2, idProduto);
        pstm.setInt(3, quantidade);

        pstm.execute();
    }
    @Override
    public void cadastrar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object buscarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
        
    
    
    
    }



