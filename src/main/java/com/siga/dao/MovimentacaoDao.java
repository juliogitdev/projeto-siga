package com.siga.dao;

import com.siga.model.Movimentacao; 
import com.siga.model.ItemMovimentacao;
import com.siga.model.Produto;
import com.siga.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDao implements InterfaceDao{
    
    ProdutoDao pd = new ProdutoDao();
    UsuarioDao ud = new UsuarioDao();
    RequisitanteDao rd = new RequisitanteDao();
    FornecedorDao fd = new FornecedorDao();


    public boolean realizarMovimentacaoCompleta(Movimentacao mov) throws Exception{
        Connection conn = null;
        
        //Daos
        ProdutoDao pd = new ProdutoDao();
        ItemMovimentacaoDao imd = new ItemMovimentacaoDao();
        
        try{
            //Pega conexao com banco
            conn = ConnectionFactory.getConnection();
            
            //Remove autocomit
            conn.setAutoCommit(false);
            
            //Cadastra nova movimentação retornando o id novo da movimentação
            System.out.println("Cadastrando movimentação");
            int idNovoMovimentacao = cadastrarMovimentação(mov, conn);
            
            System.out.println("Cadastrando itens movimentação");
            //Percorre por cada itemMovimentação
            for(ItemMovimentacao im : mov.getItemMovimentacao()){
                //Cadastra ItemMovimentação
                
                im.setIdMovimentacao(idNovoMovimentacao);
                imd.cadastrar(conn, im);

                Produto pItemMovimentacao = pd.buscarPorId(im.getIdProduto());

                System.out.println("Atualizando estoque");
                //Atualiza estoque
                switch(mov.getTipo().toUpperCase()){
                    case "ENTRADA":
                        pd.adicionarEstoque(pItemMovimentacao, conn, im.getQuantidade());
                        break;
                    case "SAIDA":
                        pd.removerEstoque(pItemMovimentacao, conn, im.getQuantidade());
                }
                
            }
            
            conn.commit();
            return true;
            
        }catch(Exception e){
            conn.rollback();
            System.out.println("Falha ao cadastrar movimentação, nada foi inserido");;
            return false;
        }
    }
    
    private int cadastrarMovimentação(Movimentacao mov, Connection conn) throws SQLException {
        String sql = "INSERT INTO movimentacao (data_hora, tipo,  id_usuario, id_requisitante, id_fornecedor) VALUES (?, ?, ?, ?, ?);";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { 
            
            pstm.setTimestamp(1, mov.getDataHora());
            pstm.setString(2, mov.getTipo());
            pstm.setInt(3, mov.getUsuario().getId());
            if(mov.getTipo().equals("ENTRADA")){
                pstm.setNull(4, java.sql.Types.INTEGER);
                pstm.setInt(5, mov.getFornecedor().getId());
            }else{
                pstm.setInt(4, mov.getRequisitante().getId());
                pstm.setNull(5, java.sql.Types.INTEGER);
            }
            
            int linhasAfetada = pstm.executeUpdate();
            
            if(linhasAfetada == 0){
                throw new SQLException("Falha ao cadastrar movimentação, nenhuma linha afetada");
            }
            try(ResultSet rs = pstm.getGeneratedKeys()){
                if(rs.next()){
                    return rs.getInt(1);
                }else{
                    throw new SQLException("Falha ao cadastrar movimentação, não retornou o novo id gerado");
                }
            }
            
        }
    }

    
    public List<Movimentacao> listarTodos() throws SQLException {
        String sql = "SELECT id_movimentacao, data_hora, tipo, id_usuario, id_requisitante, id_fornecedor FROM movimentacao;";
        List<Movimentacao> lista = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) { 
            
            
            
            while (rs.next()) {
                Movimentacao mov = new Movimentacao();
                mov.setId(rs.getInt("id_movimentacao"));
                mov.setData_hora(rs.getTimestamp("data_hora"));
                mov.setTipo(rs.getString("tipo"));
                mov.setUsuario(ud.buscarPorId(rs.getInt("id_usuario")));
                mov.setRequisitante(rd.buscarPorId(rs.getInt("id_requisitante")));
                mov.setFornecedor(fd.buscarPorId(rs.getInt("id_fornecedor")));
                
                lista.add(mov);
            }
        }
        return lista;
    }

    
    public Movimentacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_movimentacao, data_hora, tipo, id_usuario, id_requisitante, id_fornecedor FROM movimentacao WHERE id_movimentacao = ?;";
        Movimentacao mov = null;
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, id);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    mov = new Movimentacao();
                    mov.setId(rs.getInt("id_movimentacao"));
                    mov.setData_hora(rs.getTimestamp("data_hora"));
                    mov.setTipo(rs.getString("tipo"));
                    mov.setUsuario(ud.buscarPorId(rs.getInt("id_usuario")));
                    mov.setRequisitante(rd.buscarPorId(rs.getInt("id_requisitante")));
                    mov.setFornecedor(fd.buscarPorId(rs.getInt("id_fornecedor")));
                    
                }
            }
        }
        return mov;
    }

    @Override
    public void cadastrar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(Object Entidade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List listarAtivos(boolean bool) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}