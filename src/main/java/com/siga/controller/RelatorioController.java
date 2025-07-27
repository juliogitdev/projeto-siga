package com.siga.controller;

import com.siga.dao.RelatorioDao;
import com.siga.dto.*;
import com.siga.view.RelatorioView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;

public class RelatorioController {

    private RelatorioView view;
    private RelatorioDao dao;

    public RelatorioController() {
        this.view = new RelatorioView();
        this.dao = new RelatorioDao();

        initController();
    }

    private void initController() {
        view.getBtnGerar().addActionListener(e -> {
            try {
                gerarRelatorio();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Erro ao gerar relatório: " + ex.getMessage());
            }
        });
    }
    
    public void setVisible(boolean b){
        view.setVisible(b);
    }
    

    private <T> void atualizarTabelaGenerica(List<T> lista, String[] colunas, Function<T, Object[]> mapLinha) {
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        for (T item : lista) {
            model.addRow(mapLinha.apply(item));
        }
        view.getTabela().setModel(model);
    }

    private void gerarRelatorio() throws SQLException {
        String entidade = (String) view.getComboEntidade().getSelectedItem();
        String relatorio = (String) view.getComboRelatorio().getSelectedItem();

        if (entidade.equals("Produto")) {
            switch (relatorio) {
                case "Produtos mais movimentados" -> {
                    List<RelatorioProdutoMaisMovimentado> lista = dao.buscarProdutosMaisMovimentados();
                    atualizarTabelaGenerica(lista,
                            new String[]{"Produto", "Entradas", "Saídas", "Total Movimentado"},
                            p -> new Object[]{
                                    p.getNomeProduto(),
                                    p.getTotalEntradas(),
                                    p.getTotalSaidas(),
                                    p.getTotalMovimentado()
                            });
                }
                case "Histórico de movimentação" -> {
                    // Para exemplo, usar idProduto fixo ou pode abrir diálogo para escolher
                    int idProduto = pedirIdProduto(); // você pode implementar um método de entrada para usuário escolher
                    List<HistoricoMovimentacaoProduto> lista = dao.buscarHistoricoMovimentacaoProduto(idProduto);
                    atualizarTabelaGenerica(lista,
                            new String[]{"Data/Hora", "Tipo", "Quantidade", "Entidade", "Tipo Entidade"},
                            h -> new Object[]{
                                    h.getDataHora(),
                                    h.getTipoMovimentacao(),
                                    h.getQuantidade(),
                                    h.getNomeEntidade(),
                                    h.getTipoEntidade()
                            });
                }
                case "Produtos com mais estoque" -> {
                    List<RelatorioEstoqueProduto> lista = dao.buscarProdutosMaisEstoque();
                    atualizarTabelaGenerica(lista,
                            new String[]{"Produto", "Estoque"},
                            p -> new Object[]{p.getProduto(), p.getQuantidadeAtual()});
                }
                case "Produtos com menos estoque" -> {
                    List<RelatorioEstoqueProduto> lista = dao.buscarProdutosMenosEstoque();
                    atualizarTabelaGenerica(lista,
                            new String[]{"Produto", "Estoque"},
                            p -> new Object[]{p.getProduto(), p.getQuantidadeAtual()});
                }

            }
        } else if (entidade.equals("Requisitante")) {
            if (relatorio.equals("Movimentações por requisitante")) {
                List<RelatorioMovimentacaoRequisitante> lista = dao.buscarPorRequisitante();
                atualizarTabelaGenerica(lista,
                        new String[]{"ID", "Tipo", "Data/Hora", "Produto", "Requisitante"},
                        r -> new Object[]{
                                r.getId(),
                                r.getTipo(),
                                r.getDataHora(),
                                r.getProduto().getNomeProduto(),
                                r.getEntidade().getNome()
                        });
            }
            else if (relatorio.equals("Total de requisições por requisitante")) {
                List<TotalRequisicoesRequisitante> lista = dao.buscarTotalRequisicoesPorRequisitante();
                atualizarTabelaGenerica(lista,
                        new String[]{"Requisitante", "Total de Requisições"},
                        r -> new Object[]{r.getRequisitante(), r.getTotalRequisicoes()});
            }

        } else if (entidade.equals("Fornecedor")) {
            if (relatorio.equals("Movimentações por fornecedor")) {
                List<RelatorioMovimentacaoFornecedor> lista = dao.buscarPorFornecedor();
                atualizarTabelaGenerica(lista,
                        new String[]{"ID", "Tipo", "Data/Hora", "Produto", "Fornecedor"},
                        f -> new Object[]{
                                f.getId(),
                                f.getTipo(),
                                f.getDataHora(),
                                f.getProduto().getNomeProduto(),
                                f.getEntidade().getRazaoSocial()
                        });
            }
            else if (relatorio.equals("Quantidade de movimentações por fornecedor")) {
                List<TotalMovimentacoesFornecedor> lista = dao.buscarTotalMovimentacoesFornecedor();
                atualizarTabelaGenerica(lista,
                        new String[]{"Fornecedor", "Total de Movimentações"},
                        f -> new Object[]{f.getNomeFornecedor(), f.getTotalMovimentacoes()});
            }

        }
    }

    // Exemplo simples para pegar idProduto fixo - você pode melhorar pedindo input do usuário
    private int pedirIdProduto() {
        String input = JOptionPane.showInputDialog(view, "Digite o ID do produto para o relatório:");
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "ID inválido, usando 1 por padrão.");
            return 1;
        }
    }
}
