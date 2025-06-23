
package com.siga.dao;

import com.siga.model.Usuario;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDao extends MainDao implements InterfaceDao<Usuario> {
    @SuppressWarnings("empty-statement")
    @Override
    public void cadastrar(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario(nome_completo, email, senha) VALUES(?, ?, ?);";
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha()); 
            pstm.execute();
        };
    }
    @Override
    public void atualizar(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuario SET nome_completo = ?, email = ?, senha = ? where id_usuario = ?;";
        
        
        try(Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)){
           
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getEmail());
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
                u.setEmail(resultset.getString("email"));
                u.setSenha(resultset.getString("senha"));
                u.setId(resultset.getInt("id_usuario"));
                listaUsuario.add(u);
            }
            
            
        
        }
        return listaUsuario;
        
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        List<Object> lA = super.buscarPorId("usuario", id);
        Usuario u = new Usuario();
        
        u.setId((Integer)lA.get(0));
        u.setNome(String.valueOf(lA.get(1)));
        u.setEmail(String.valueOf(lA.get(2)));
        u.setSenha(String.valueOf(lA.get(3)));
        
        return u;
    }
    
    public Usuario buscarPorEmail(String email) throws SQLException{
        Usuario u = null;
        String sql = "SELECT * FROM usuario WHERE login = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setString(1, email);
            
            try(ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setEmail(rs.getString("email"));
                    u.setNome(rs.getString("nome_completo"));
                    u.setSenha(rs.getString("senha"));
                }
            }
        }
        
        return u;
    }
}
    
    
