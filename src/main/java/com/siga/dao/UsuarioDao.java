
package com.siga.dao;

import com.siga.model.Usuario;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao implements InterfaceDao<Usuario>{
    @SuppressWarnings("empty-statement")
    @Override
    public void cadastrar(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario(nome_completo, login, senha) VALUES(?, ?, ?);";
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getLogin());
            pstm.setString(3, usuario.getSenha()); 
            pstm.execute();
        };
    }
    @Override
    public void atualizar(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome_completo = ?, login = ?, senha = ? where id_usuario = ?;";
        
        
        try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
           
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getLogin());
            pstm.setString(3, usuario.getSenha());
            pstm.setInt(4, usuario.getId());
            pstm.execute();
        
        }
    }
    @Override
    public void deletar(Usuario usuario) throws SQLException{
        String sql = "DELETE FROM usuario WHERE id_usuario = ?;";
        
        try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
            pstm.setInt(1, usuario.getId());
            pstm.execute();
        }}
        
    @Override
    public List<Usuario> listarTodos() throws SQLException{
        String sql = "SELECT * FROM usuario;";
        List<Usuario> listaUsuario = new ArrayList<>();
        
        
        try(Connection connection  = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
            ResultSet resultset = pstm.executeQuery();
            
            while(resultset.next()){
                
                
                Usuario u  = new Usuario();
                u.setNome(resultset.getString("nome_completo"));
                u.setLogin(resultset.getString("login"));
                u.setSenha(resultset.getString("login"));
                u.setId(resultset.getInt("id_usuario"));
                listaUsuario.add(u);
            }
            
            
        
        }
        return listaUsuario;
        
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?;";
        Usuario u = null;
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ){
            
            pstm.setInt(1, id);
            
            try(ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setLogin(rs.getString("login"));
                    u.setNome(rs.getString("nome_completo"));
                    u.setSenha(rs.getString("senha"));
                }
            }
            
            
            
            
        }
        return u;
    }
    
    public Usuario buscarPorLogin(String login) throws SQLException{
        Usuario u = null;
        String sql = "SELECT * FROM usuario WHERE login = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setString(1, login);
            
            try(ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setLogin(rs.getString("login"));
                    u.setNome(rs.getString("nome_completo"));
                    u.setSenha(rs.getString("senha"));
                }
            }
        }
        
        return u;
    }
}
    
    
