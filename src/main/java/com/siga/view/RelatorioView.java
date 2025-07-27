package com.siga.view;

import javax.swing.*;
import java.awt.*;

public class RelatorioView extends JFrame {
    private JComboBox<String> comboEntidade;
    private JComboBox<String> comboRelatorio;
    private JButton btnGerar;
    private JTable tabela;
    private JScrollPane scrollPane;

    public RelatorioView() {
        setTitle("Relatórios");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltro.add(new JLabel("Entidade:"));
        comboEntidade = new JComboBox<>(new String[]{"Produto", "Requisitante", "Fornecedor"});
        painelFiltro.add(comboEntidade);

        painelFiltro.add(new JLabel("Relatório:"));
        comboRelatorio = new JComboBox<>();
        painelFiltro.add(comboRelatorio);

        btnGerar = new JButton("Gerar Relatório");
        painelFiltro.add(btnGerar);

        add(painelFiltro, BorderLayout.NORTH);

        tabela = new JTable();
        scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Atualiza os relatórios ao mudar a entidade
        comboEntidade.addActionListener(e -> atualizarOpcoesRelatorio());

        atualizarOpcoesRelatorio();

        setVisible(false);
    }

    private void atualizarOpcoesRelatorio() {
        comboRelatorio.removeAllItems();
        String entidade = (String) comboEntidade.getSelectedItem();

        if (entidade.equals("Produto")) {
            comboRelatorio.addItem("Produtos mais movimentados");
            comboRelatorio.addItem("Histórico de movimentação");
            comboRelatorio.addItem("Produtos com mais estoque"); // novo
            comboRelatorio.addItem("Produtos com menos estoque"); // novo
        } else if (entidade.equals("Requisitante")) {
            comboRelatorio.addItem("Movimentações por requisitante");
            comboRelatorio.addItem("Total de requisições por requisitante"); // novo
        } else if (entidade.equals("Fornecedor")) {
            comboRelatorio.addItem("Movimentações por fornecedor");
            comboRelatorio.addItem("Quantidade de movimentações por fornecedor"); // novo
        }

    }

    // Getters para controller
    public JComboBox<String> getComboEntidade() {
        return comboEntidade;
    }

    public JComboBox<String> getComboRelatorio() {
        return comboRelatorio;
    }

    public JButton getBtnGerar() {
        return btnGerar;
    }

    public JTable getTabela() {
        return tabela;
    }
}
