package com.siga.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import com.siga.model.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao implements InterfaceDao<Categoria>{
    @Override
    public void cadastrar(Categoria categoria) throws SQLException{
        
        String sql = "INSERT INTO categoria(nome, descricao) VALUES (?, ?);";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);)
        { 
            pstm.setString(1, categoria.getNome_categoria());
            pstm.setString(2, categoria.getDescricao());
            
            pstm.execute();
            
            System.out.println("Categoria " + categoria.getNome_categoria() + " adicionada com sucesso.");
        }
    }
    @Override
    public void atualizar(Categoria categoria) throws SQLException{
        String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id_categoria = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            
            pstm.setString(1, categoria.getNome_categoria());
            pstm.setString(2, categoria.getDescricao());
            pstm.setInt(3, categoria.getId());
            pstm.execute();
            
            System.out.println("Categoria " + categoria.getNome_categoria() + " atualizada com sucesso.");
            
            
        }
    }
    @Override
    public void deletar(Categoria categoria) throws SQLException{
        String sql = "DELETE FROM categoria WHERE id_categoria = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            
            pstm.setInt(1, categoria.getId());
            pstm.execute();
            
        }
        
    }
    @Override
    public List<Categoria> listarTodos()throws SQLException{
        String sql = "SELECT * FROM categoria;";
        List<Categoria> lista = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery(sql);)
        {
            
            while(rs.next()){
                Categoria novaCategoria = new Categoria();
                novaCategoria.setId(rs.getInt("id_categoria"));
                novaCategoria.setNome_categoria(rs.getString("nome"));
                novaCategoria.setDescricao(rs.getString("descricao"));
                
                lista.add(novaCategoria);
            }
            
        }
        return lista;
    }
    
}
