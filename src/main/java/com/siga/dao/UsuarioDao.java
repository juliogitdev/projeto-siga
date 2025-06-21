
package com.siga.dao;

import com.siga.model.Usuario;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao {
    @SuppressWarnings("empty-statement")
    public static void cadastrar(Usuario usuario) throws SQLException{
        
        String sql = "INSERT INTO usuario(nome_completo, login, senha) VALUES(?, ?, ?);";
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getLogin());
            pstm.setString(3, usuario.getSenha()); 
            pstm.execute();
        };
    }
    
    public static void atualizar(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome_completo = ?, login = ?, senha = ? where id_usuario = ?;";
        
        
        try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
           
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getLogin());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4, usuario.getId_usuario());
            pstm.execute();
        
        }
    }
        
    public static void deletar(Usuario usuario) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setInt(1, usuario.getId_usuario());
            pstm.execute();
        }}
        
   
    public static ArrayList<Usuario> listar() throws SQLException{
        String sql = "SELECT * FROM usuario";
        ArrayList<Usuario> lista_usuario = new ArrayList<>();
        
        
        try(Connection connection  = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
            ResultSet resultset = pstm.executeQuery();
            
            while(resultset.next()){
                
                
                Usuario u  = new Usuario();
                u.setNome(resultset.getString("nome_completo"));
                u.setLogin(resultset.getString("login"));
                u.setSenha(resultset.getString("login"));
                u.setId_usuario(resultset.getInt("id_usuario"));
                lista_usuario.add(u);
            }
            
            
        return lista_usuario;
        }
        
        
    }
}
    
    
