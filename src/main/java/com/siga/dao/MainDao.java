
package com.siga.dao;

import com.siga.model.Entidade;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public abstract class MainDao implements InterfaceDao{
    
    private List<String> buscarNomesColunas(String table) throws SQLException {
    String sql = "SELECT * FROM " + table;
    int qtColunas = 0;
    List<String> listaNomes = new ArrayList<>();
    
    try (Connection conn = ConnectionFactory.getConnection(); Statement s = conn.createStatement()){
        s.executeQuery(sql);
        ResultSet rs = s.getResultSet();
        ResultSetMetaData rsmd = rs.getMetaData();
        qtColunas = rsmd.getColumnCount();
        
        for(int i= 1; i<=qtColunas; i++){
            listaNomes.add(rsmd.getColumnLabel(i));
        
        }
    return listaNomes;
    }
        
    
    
    }
    
    private int contarColunas(String tabela) throws SQLException{
        int qtColunas;
        String sql = "SELECT * FROM " + tabela;
        
        
        try(Connection conn = ConnectionFactory.getConnection(); Statement s = conn.createStatement()){
            
            try(ResultSet rs = s.executeQuery(sql);){
            
                ResultSetMetaData rsmd = rs.getMetaData();
                qtColunas=rsmd.getColumnCount();
            }
        }
        
        return qtColunas;
    
    }
   

    
    public List<Object> buscarPorId(String tabela, int id) throws SQLException{
        
        List<String> lista = buscarNomesColunas(tabela);
        String colunaId = lista.get(0);
        String sql = "SELECT * FROM " + tabela + " WHERE " +colunaId + " = ?";
        List<Object> listaAtributos = new ArrayList<>();
        
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            
            pstm.setInt(1, id);
                
            try(ResultSet rs = pstm.executeQuery()){
                int qtColunas = rs.getMetaData().getColumnCount();
                
                if(rs.next()){
                    for(int i = 1; i<=qtColunas; i++){
                        listaAtributos.add(rs.getObject(i));
                    }
                }      
            }
            
        }
        
        return listaAtributos;
    }
    
    public static void deletarPermanentemente(String tipoEntidade, Entidade ent) throws SQLException{
        String sql = "DELETE FROM " + tipoEntidade + " WHERE id_categoria = ?;";
        
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);){
            
            pstm.setInt(1, ent.getId());
            pstm.execute();
            
        }
    
    }

    @Override
    public abstract void cadastrar(Object Entidade) throws SQLException;
    @Override
    public abstract void atualizar(Object Entidade) throws SQLException;

    @Override
    public abstract void deletar(Object Entidade) throws SQLException;

    @Override
    public abstract List listarTodos() throws SQLException;

    @Override
    public abstract Object buscarPorId(int id)throws SQLException;
}

