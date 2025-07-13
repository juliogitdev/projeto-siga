package com.siga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.siga.util.ConnectionFactory;
import com.siga.model.Categoria;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;

import com.siga.dao.CategoriaDao;
import com.siga.dao.FornecedorDao;


public class ProdutoDao implements InterfaceDao<Produto>{

    @Override
    public void cadastrar(Produto p) throws SQLException {
        String sql = "INSERT INTO produto (nome_produto, descricao, quantidade, id_fornecedor, id_categoria) VALUES (?, ?, ?, ?, ?);";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            
            pstm.setString(1, p.getNomeProduto());
            pstm.setString(2, p.getDescricao());
            pstm.setInt(3, p.getQuantidade());
            
            if(p.getFornecedor() ==null){
                pstm.setNull(4, java.sql.Types.INTEGER);
            }else{
                pstm.setInt(4, p.getFornecedor().getId());
            }
            
            if(p.getCategoria() == null){
                pstm.setNull(5, java.sql.Types.INTEGER);
            }else{
                pstm.setInt(5, p.getCategoria().getId());
            }
            pstm.execute();
            
        }
        
    }

    @Override
    public void atualizar(Produto p) throws SQLException {
        String sql = "UPDATE produto SET nome_produto = ?, descricao = ?, quantidade = ?, id_fornecedor = ?, id_categoria = ? WHERE id_produto = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            
            pstm.setString(1, p.getNomeProduto());
            pstm.setString(2, p.getDescricao());
            pstm.setInt(3, p.getQuantidade());
            pstm.setInt(6, p.getId());
            
            if(p.getFornecedor() ==null){
                pstm.setNull(4, java.sql.Types.INTEGER);
            }else{
                pstm.setInt(4, p.getFornecedor().getId());
            }
            
            if(p.getCategoria() == null){
                pstm.setNull(5, java.sql.Types.INTEGER);
            }else{
                pstm.setInt(5, p.getCategoria().getId());
            }
            
            
            pstm.execute();
            
        }
        
    }

    @Override
    public void deletar(Produto p) throws SQLException {
        String sql = "DELETE FROM produto WHERE id_produto = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            
            pstm.setInt(1, p.getId());
            
        }
        
    }

    @Override
    public List<Produto> listarTodos() throws SQLException {
        List<Produto> listaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();){
            
            while(rs.next()){
                Produto novoProduto = new Produto();
                FornecedorDao fDao = new FornecedorDao();
                CategoriaDao cDao = new CategoriaDao();
                
                novoProduto.setId(rs.getInt("id_produto"));
                novoProduto.setNomeProduto(rs.getString("nome_produto"));
                novoProduto.setDescricao(rs.getString("descricao"));
                novoProduto.setFornecedor(fDao.buscarPorId(rs.getInt("id_fornecedor")));
                novoProduto.setCategoria(cDao.buscarPorId(rs.getInt("id_categoria")));
                
                listaProdutos.add(novoProduto);
            }
        };
        
        return listaProdutos;
    }

    @Override
    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produto WHERE id_produto=?;";
        Produto novoProduto = null;
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ){
            
            pstm.setInt(1, id);
            
            try(ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    FornecedorDao fDao = new FornecedorDao();
                    CategoriaDao cDao = new CategoriaDao();
                    novoProduto = new Produto();
                

                    novoProduto.setId(rs.getInt("id_produto"));
                    novoProduto.setNomeProduto(rs.getString("nome_produto"));
                    novoProduto.setDescricao(rs.getString("descricao"));
                    novoProduto.setFornecedor(fDao.buscarPorId(rs.getInt("id_fornecedor")));
                    novoProduto.setCategoria(cDao.buscarPorId(rs.getInt("id_categoria")));
                }
                
            }
        }
        return novoProduto;
        
    }
    
    public void atualizarEstoque(Produto p) throws SQLException{
        String sql = "UPDATE produto SET quantidade = ? WHERE id_produto = ?";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            
            pstm.setInt(1, p.getQuantidade());
            pstm.setInt(2, p.getId());
            
         
            pstm.execute();
            
        }
        
    }
    
}
