package com.siga.dao;

import com.siga.model.Categoria;
import com.siga.model.Requisitante;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequisitanteDao implements InterfaceDao<Requisitante>{

    @Override
    public void cadastrar(Requisitante re) throws SQLException {
        String sql = "INSERT INTO requisitante(nome_requisitante, setor, endereco) VALUES(?, ?, ?);";
        try(Connection conn = ConnectionFactory.getConnection();
                PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setString(1, re.getNome());
            System.out.println("get nome");
            pstm.setString(2, re.getSetor());
            System.out.println("get setor");
            pstm.setString(3, re.getEndereco());
            System.out.println("get endereco");
            pstm.execute();
            System.out.println("executado");
        }
    }
    
    @Override
    public void atualizar(Requisitante re) throws SQLException {
        String sql = "UPDATE requisitante SET nome_requisitante = ?, setor = ?, endereco = ? where id_requisitante = ?;";
        
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, re.getNome());
            pstm.setString(2, re.getSetor());
            pstm.setString(3, re.getEndereco());
            pstm.setInt(4, re.getId());
            
            pstm.execute();
        }
    }
        
    @Override
    public void deletar(Requisitante re) throws SQLException {
        String sql = "DELETE FROM requisitante WHERE id_requisitante = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setInt(1, re.getId());
            
            pstm.execute();
        }
    }

    @Override
    public List listarTodos() throws SQLException {
        String sql = "SELECT * FROM requisitante;";
        List<Requisitante> listaRequisitante = new ArrayList<>();
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();){
            
            while(rs.next()){
                Requisitante re = new Requisitante();
                re.setId(rs.getInt("id_requisitante"));
                re.setNome(rs.getString("nome_requisitante"));
                re.setSetor(rs.getString("setor"));
                re.setEndereco(rs.getString("endereco"));
                listaRequisitante.add(re);
            }
            
        }
        return listaRequisitante;
    }

    @Override
    public Requisitante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM requisitante WHERE id_requisitante = ?;";
        Requisitante novoRequisitante = null;
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, id);
            
            
            try(ResultSet rs = pstm.executeQuery()){
                
                if (rs.next()){
                    novoRequisitante = new Requisitante();

                    novoRequisitante.setNome(rs.getString("nome_requisitante"));
                    novoRequisitante.setEndereco(rs.getString("endereco"));
                    novoRequisitante.setSetor(rs.getString("setor"));
                    novoRequisitante.setId(rs.getInt("id_requisitante"));
                }
            }
        
        
        }
        
        
        return novoRequisitante;
    }
    public Requisitante buscarPorNomeExato(String nome) throws SQLException{
        Requisitante r = null;
        String sql = "SELECT * FROM requisitante WHERE nome_requisitante = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setString(1, nome);
            
            try(ResultSet rs = pstm.executeQuery();){
                if(rs.next()){
                    r = new Requisitante();
                    r.setId(rs.getInt("id_requisitante"));
                    r.setNome(rs.getString("nome_requisitante"));
                    r.setSetor(rs.getString("setor"));
                    r.setEndereco(rs.getString("endereco"));
                }
            }
        }
        
        return r;
    }
    
}

