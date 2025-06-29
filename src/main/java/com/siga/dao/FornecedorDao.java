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
public class FornecedorDao extends MainDao implements InterfaceDao<Fornecedor> {

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
    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        List<Object> lA = super.buscarPorId("fornecedor", id);
        Fornecedor f = new Fornecedor();
        
        f.setId((Integer)lA.get(0));
        f.setRazaoSocial(String.valueOf(lA.get(1)));
        f.setCnpj(String.valueOf(lA.get(2)));
        f.setTelefone(String.valueOf(lA.get(3)));
        f.setEndereco(String.valueOf(lA.get(4)));
        f.setEmail(String.valueOf(lA.get(5)));
        
        return f;
    }
    
    
    
}
        


