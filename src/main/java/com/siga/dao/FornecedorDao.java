/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.dao;

import com.siga.model.Fornecedor;
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
public class FornecedorDao implements InterfaceDao<Fornecedor> {

    @Override
    @SuppressWarnings("empty-statement")
    public void cadastrar(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO Fornecedor(razao_social, cnpj, telefone, endereco, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getRazaoSocial());
            pstm.setString(2, f.getCnpj());
            pstm.setString(3, f.getTelefone());
            pstm.setString(4, f.getEndereco());
            pstm.setString(5, f.getEmail());
            pstm.execute();
        };
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void atualizar(Fornecedor f) throws SQLException {
        String sql = "UPDATE fornecedor SET razao_social = ?, cnpj = ?, telefone = ?, endereco = ?, email = ? where id_fornecedor = ?;";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getRazaoSocial());
            pstm.setString(2, f.getCnpj());
            pstm.setString(3, f.getTelefone());
            pstm.setString(4, f.getEndereco());
            pstm.setString(5, f.getEmail());
            pstm.setInt(6, f.getId());
            pstm.execute();
        };
    }

    @Override
    public void deletar(Fornecedor f) throws SQLException {
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor = ?;";

        try (Connection connection = ConnectionFactory.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, f.getId());
            pstm.execute();
        }
    }

    @Override
    public List<Fornecedor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> listaFornecedor = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                f.setEndereco(rs.getString("endereco"));
                f.setEmail(rs.getString("email"));
                listaFornecedor.add(f);
            }
        }
        return listaFornecedor;
    }
    
    public Fornecedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?;";
        Fornecedor f = null; // Comece com nulo

        // O try-with-resources garante o fechamento automático da conexão e do statement
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

        
            pstm.setInt(1, id);

        
            try(ResultSet rs = pstm.executeQuery()){

            
                if (rs.next()) {
                    f = new Fornecedor(); // Crie o objeto só se encontrou algo
                
                    f.setId(rs.getInt("id_fornecedor"));
                    f.setRazaoSocial(rs.getString("razao_social"));
                    f.setCnpj(rs.getString("cnpj"));
                    f.setEndereco(rs.getString("endereco"));
                    f.setTelefone(rs.getString("telefone"));
                    f.setEmail(rs.getString("email"));
                }
            }
        }
    
        // Retorna o fornecedor encontrado ou null se não achou
        return f;
}
    public Fornecedor buscarPorCnpj(String cnpj) throws SQLException{
        String sql = "SELECT * FROM fornecedor WHERE cnpj = ?;";
        Fornecedor f = null; // Comece com nulo

        // O try-with-resources garante o fechamento automático da conexão e do statement
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);) {
            
            
            pstm.setString(1, cnpj);

        
            try(ResultSet rs = pstm.executeQuery()){

            
                if (rs.next()) {
                    f = new Fornecedor(); // Crie o objeto só se encontrou algo
                
                    f.setId(rs.getInt("id_fornecedor"));
                    f.setRazaoSocial(rs.getString("razao_social"));
                    f.setCnpj(rs.getString("cnpj"));
                    f.setEndereco(rs.getString("endereco"));
                    f.setTelefone(rs.getString("telefone"));
                    f.setEmail(rs.getString("email"));
                }
            }
        }
    
        // Retorna o fornecedor encontrado ou null se não achou
        return f;
    }
        
}


