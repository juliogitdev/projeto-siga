package com.siga.dao;

import com.siga.dto.*;
import com.siga.model.Produto;
import com.siga.model.Fornecedor;
import com.siga.model.Requisitante;
import com.siga.util.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDao {

    public List<RelatorioProdutoMaisMovimentado> buscarProdutosMaisMovimentados() throws SQLException {
        String sql = "SELECT p.nome_produto AS nome, " +
                "SUM(CASE WHEN m.tipo = 'ENTRADA' THEN im.quantidade ELSE 0 END) AS total_entradas, " +
                "SUM(CASE WHEN m.tipo = 'SAIDA' THEN im.quantidade ELSE 0 END) AS total_saidas, " +
                "SUM(im.quantidade) AS total_movimentado " +
                "FROM item_movimentacao im " +
                "JOIN produto p ON im.id_produto = p.id_produto " +
                "JOIN movimentacao m ON im.id_movimentacao = m.id_movimentacao " +
                "GROUP BY p.nome_produto " +
                "ORDER BY total_movimentado DESC";

        List<RelatorioProdutoMaisMovimentado> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                RelatorioProdutoMaisMovimentado p = new RelatorioProdutoMaisMovimentado(
                        rs.getString("nome"),
                        rs.getInt("total_entradas"),
                        rs.getInt("total_saidas"),
                        rs.getInt("total_movimentado")
                );
                lista.add(p);
            }
        }
        System.out.println(lista);
        return lista;
    }

    public List<HistoricoMovimentacaoProduto> buscarHistoricoMovimentacaoProduto(int idProduto) throws SQLException {
        String sql = "SELECT m.data_hora, m.tipo, im.quantidade, " +
                "COALESCE(f.razao_social, r.nome_requisitante) AS nome_entidade, " +
                "CASE " +
                "  WHEN f.id_fornecedor IS NOT NULL THEN 'Fornecedor' " +
                "  WHEN r.id_requisitante IS NOT NULL THEN 'Requisitante' " +
                "  ELSE 'Desconhecido' " +
                "END AS tipo_entidade " +
                "FROM movimentacao m " +
                "JOIN item_movimentacao im ON m.id_movimentacao = im.id_movimentacao " +
                "LEFT JOIN fornecedor f ON m.id_fornecedor = f.id_fornecedor " +
                "LEFT JOIN requisitante r ON m.id_requisitante = r.id_requisitante " +
                "WHERE im.id_produto = ? " +
                "ORDER BY m.data_hora DESC";

        List<HistoricoMovimentacaoProduto> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, idProduto);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    lista.add(new HistoricoMovimentacaoProduto(
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            rs.getString("tipo"),
                            rs.getInt("quantidade"),
                            rs.getString("nome_entidade"),
                            rs.getString("tipo_entidade")
                    ));
                }
            }
        }
        return lista;
    }

    public List<RelatorioMovimentacaoRequisitante> buscarPorRequisitante() throws SQLException {
        String sql = "SELECT m.id_movimentacao, m.tipo, m.data_hora, p.id_produto, p.nome_produto, r.id_requisitante, r.nome_requisitante, im.quantidade " +
                "FROM movimentacao m " +
                "JOIN requisitante r ON m.id_requisitante = r.id_requisitante " +
                "JOIN item_movimentacao im ON m.id_movimentacao = im.id_movimentacao " +
                "JOIN produto p ON im.id_produto = p.id_produto " +
                "WHERE m.tipo = 'SAIDA' " +
                "ORDER BY m.data_hora DESC";

        List<RelatorioMovimentacaoRequisitante> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Requisitante req = new Requisitante();
                req.setId(rs.getInt("id_requisitante"));
                req.setNome(rs.getString("nome_requisitante"));

                lista.add(new RelatorioMovimentacaoRequisitante(
                        rs.getInt("id_movimentacao"),
                        rs.getString("tipo"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        produto,
                        req
                ));
            }
        }
        return lista;
    }


    public List<RelatorioMovimentacaoFornecedor> buscarPorFornecedor() throws SQLException {
        String sql = "SELECT m.id_movimentacao, m.tipo, m.data_hora, p.id_produto, p.nome_produto, f.id_fornecedor, f.razao_social, im.quantidade " +
                "FROM movimentacao m " +
                "JOIN fornecedor f ON m.id_fornecedor = f.id_fornecedor " +
                "JOIN item_movimentacao im ON m.id_movimentacao = im.id_movimentacao " +
                "JOIN produto p ON im.id_produto = p.id_produto " +
                "WHERE m.tipo = 'ENTRADA' " +
                "ORDER BY m.data_hora DESC";

        List<RelatorioMovimentacaoFornecedor> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNomeProduto(rs.getString("nome_produto"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id_fornecedor"));
                fornecedor.setRazaoSocial(rs.getString("razao_social"));

                lista.add(new RelatorioMovimentacaoFornecedor(
                        rs.getInt("id_movimentacao"),
                        rs.getString("tipo"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        produto,
                        fornecedor
                ));
            }
        }
        return lista;
    }

    public List<TotalRequisicoesRequisitante> buscarTotalRequisicoesPorRequisitante() throws SQLException {
        List<TotalRequisicoesRequisitante> lista = new ArrayList<>();
        String sql = """
        SELECT r.id_requisitante, r.nome_requisitante, r.setor, r.endereco, COUNT(m.id_movimentacao) AS total
        FROM requisitante r
        LEFT JOIN movimentacao m ON r.id_requisitante = m.id_requisitante
        WHERE r.ativo = true
        GROUP BY r.id_requisitante, r.nome_requisitante, r.setor, r.endereco
        ORDER BY total DESC
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Requisitante req = new Requisitante();
                req.setId(rs.getInt("id_requisitante"));
                req.setNome(rs.getString("nome_requisitante"));
                req.setSetor(rs.getString("setor"));
                req.setEndereco(rs.getString("endereco"));

                lista.add(new TotalRequisicoesRequisitante(req, rs.getInt("total")));
            }
        }

        return lista;
    }

    public List<TotalMovimentacoesFornecedor> buscarTotalMovimentacoesFornecedor() throws SQLException {
        List<TotalMovimentacoesFornecedor> lista = new ArrayList<>();
        String sql = """
        SELECT f.id_fornecedor, f.razao_social, COUNT(m.id_movimentacao) AS total
        FROM fornecedor f
        LEFT JOIN movimentacao m ON f.id_fornecedor = m.id_fornecedor
        WHERE f.ativo = true
        GROUP BY f.id_fornecedor, f.razao_social
        ORDER BY total DESC
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new TotalMovimentacoesFornecedor(
                        rs.getInt("id_fornecedor"),
                        rs.getString("razao_social"),
                        rs.getInt("total")
                ));
            }
        }

        return lista;
    }

    public List<RelatorioEstoqueProduto> buscarProdutosMaisEstoque() throws SQLException {
        List<RelatorioEstoqueProduto> lista = new ArrayList<>();
        String sql = """
        SELECT id_produto, nome_produto, descricao, quantidade
        FROM produto
        WHERE ativo = true
        ORDER BY quantidade DESC
        LIMIT 10
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNomeProduto(rs.getString("nome_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));

                lista.add(new RelatorioEstoqueProduto(p, p.getQuantidade()));
            }
        }

        return lista;
    }

    public List<RelatorioEstoqueProduto> buscarProdutosMenosEstoque() throws SQLException {
        List<RelatorioEstoqueProduto> lista = new ArrayList<>();
        String sql = """
        SELECT id_produto, nome_produto, descricao, quantidade
        FROM produto
        WHERE ativo = true
        ORDER BY quantidade ASC
        LIMIT 10
    """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNomeProduto(rs.getString("nome_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));

                lista.add(new RelatorioEstoqueProduto(p, p.getQuantidade()));
            }
        }

        return lista;
    }



}
