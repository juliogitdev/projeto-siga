package com.siga.dao;

import com.siga.model.Movimentacao; 
import com.siga.model.Requisitante;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;

import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDao implements InterfaceDao<Movimentacao> {
    
    ProdutoDao pd = new ProdutoDao();
    UsuarioDao ud = new UsuarioDao();
    RequisitanteDao rd = new RequisitanteDao();

    @Override
    public void cadastrar(Movimentacao movimentacao) throws SQLException {
        String sql = "INSERT INTO movimentacao (data_hora, quantidade, tipo, id_produto, id_usuario, id_requisitante) VALUES (?, ?, ?, ?, ?, ?);";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { 

            pstm.setTimestamp(1, java.sql.Timestamp.valueOf(movimentacao.getDataHora())); 
            pstm.setInt(2, movimentacao.getQuantidade());
            pstm.setString(3, movimentacao.getTipo()); 
            pstm.setInt(4, movimentacao.getProduto().getId());
            pstm.setInt(5, movimentacao.getUsuario().getId());
            pstm.setInt(6, movimentacao.getEntidade().getId());
            
            
            pstm.execute();

            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    movimentacao.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void atualizar(Movimentacao movimentacao) throws SQLException {
        String sql = "UPDATE movimentacao SET data_hora = ?, quantidade = ?, tipo = ?, id_produto = ?, id_usuario = ?, id_requisitante = ? WHERE id_movimentacao = ?;";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setTimestamp(1, java.sql.Timestamp.valueOf(movimentacao.getDataHora()));
            pstm.setInt(2, movimentacao.getQuantidade());
            pstm.setString(3, movimentacao.getTipo());
            pstm.setInt(4, movimentacao.getProduto().getId());
            pstm.setInt(5, movimentacao.getUsuario().getId());
            pstm.setInt(6, movimentacao.getEntidade().getId());
            pstm.setInt(7, movimentacao.getId());
            
            pstm.execute();
        }
    }

    @Override
    public void deletar(Movimentacao movimentacao) throws SQLException {
        String sql = "DELETE FROM movimentacao WHERE id_movimentacao = ?;";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, movimentacao.getId());
            pstm.execute();
        }
    }

    @Override
    public List<Movimentacao> listarTodos() throws SQLException {
        String sql = "SELECT id_movimentacao, data_hora, quantidade, tipo, id_produto, id_usuario, id_requisitante FROM movimentacao;";
        List<Movimentacao> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) { 
            
            
            
            while (rs.next()) {
                Movimentacao mov = new Movimentacao();
                mov.setId(rs.getInt("id"));
                mov.setData_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                mov.setQuantidade(rs.getInt("quantidade"));
                mov.setTipo(rs.getString("tipo"));
                mov.setProduto(pd.buscarPorId(rs.getInt("id_produto")));
                mov.setUsuario(ud.buscarPorId(rs.getInt("id_usuario")));
                mov.setEntidade(rd.buscarPorId(rs.getInt("id_requisitante")));
                
                lista.add(mov);
            }
        }
        return lista;
    }

    @Override
    public Movimentacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_requisitante, data_hora, quantidade, tipo, id_produto, id_usuario, id_requisitante FROM movimentacao WHERE id = ?;";
        Movimentacao mov = null;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, id);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    mov = new Movimentacao();
                    mov.setId(rs.getInt("id"));
                    mov.setData_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                    mov.setQuantidade(rs.getInt("quantidade"));
                    mov.setTipo(rs.getString("tipo"));
                    mov.setProduto(pd.buscarPorId(rs.getInt("id_produto")));
                    mov.setUsuario(ud.buscarPorId(rs.getInt("id_usuario")));
                    mov.setEntidade(rd.buscarPorId(rs.getInt("id_requisitante")));
                    
                }
            }
        }
        return mov;
    }
}