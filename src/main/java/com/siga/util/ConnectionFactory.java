package com.siga.util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/siga";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "qwe.123.";
    
    private static final String CONNECTION_PROPERTIES = "?serverTimezone=UTC";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL + CONNECTION_PROPERTIES, DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public static void closeConnection(ResultSet rs, PreparedStatement pstm, Connection conn){
        try{
            if(rs != null){
                rs.close();
            }
            
            if(pstm != null){
                pstm.close();
            }
            
            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            System.out.println("Erro ao fechar recurso do banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void closeConnection(PreparedStatement pstm, Connection conn){
        closeConnection(null, pstm, conn);
    }
    
    public static void closeConnection(Connection conn){
        closeConnection(null, null, conn);
    }
    
}
