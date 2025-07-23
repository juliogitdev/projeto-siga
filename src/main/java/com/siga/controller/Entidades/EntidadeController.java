package com.siga.controller.Entidades;

import com.siga.controller.PrincipalController;
import com.siga.dao.InterfaceDao;
import com.siga.dao.MainDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Entidade;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogRequisitante;
import com.siga.view.entidade.EntidadeView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Julio
 */
public abstract class EntidadeController {

    private String[] colunasString = {};
    private EntidadeView entidadeView;
    private InterfaceDao entidadeDao;
    private DialogEntidade dialogEntidade;
    private PrincipalController principalController;

    public EntidadeController(EntidadeView entidadeView, InterfaceDao entidadeDao, DialogEntidade dial) {
        this.entidadeView = entidadeView;
        this.entidadeDao = entidadeDao;
        this.dialogEntidade = dial;

        entidadeView.addEntidadeListenner(new AddEntidadeListener());
        entidadeView.editarEntidadeListener(new EditarEntidadeListener());
        entidadeView.excluirEntidadeListener(new ExcluirEntidadeListener());
        entidadeView.addFiltroComboboxListener(new FiltroComboboxListener()); // 🔥 Aqui é o novo listener
    }


    public List<InterfaceDao> getTodasEntidades() throws SQLException {
        return getEntidadeDao().listarTodos();
    }

    public String[] getColunasString() {
        return colunasString;
    }

    public void setColunasString(String[] colunasString) {
        this.colunasString = colunasString;
    }

    public void setColunasTabela(String[] colunas) {
        entidadeView.setColunasTabela(colunas);
    }

    public EntidadeView getView() {
        return entidadeView;
    }

    public void setView(EntidadeView entidadeView) {
        this.entidadeView = entidadeView;
    }

    public InterfaceDao getEntidadeDao() {
        return entidadeDao;
    }

    public void setEntidadeDao(InterfaceDao entidadeDao) {
        this.entidadeDao = entidadeDao;
    }

    public DialogEntidade getDialogEntidade() {
        return dialogEntidade;
    }

    public void setDialogEntidade(DialogEntidade dialogEntidade) {
        this.dialogEntidade = dialogEntidade;
    }

    public void listarEntidadesTabela() throws SQLException {
        String comboBoxSelect = getView().getComboView();
        
        switch (comboBoxSelect){
            case "Todos":
                
                getView().atualizarTabela(getEntidadeDao().listarTodos());
                break;
            
            case "Ativos":
                
                getView().atualizarTabela(getEntidadeDao().listarAtivos(true));
                break;
                
            case "Inativos":
                getView().atualizarTabela(getEntidadeDao().listarAtivos(false));
                break;
        }
        
        pintarTabela();
        
        
    }
    
    public void pintarTabela() throws SQLException{
        List<Entidade> entidades = getEntidadeDao().listarTodos();

        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).isEnabled()) {
                getView().adicionarLinhasColoridas(new int[]{i}, new Color(144, 238, 144));
            } else {
                getView().adicionarLinhasColoridas(new int[]{i}, new Color(255, 105, 97));
            }
        }
    
    }

    public void setVisibleDialog() {
        getDialogEntidade().setVisible(true);
    }

    public boolean isInputEmpty(JTextField textInput) {
        return textInput.getText().isBlank();
    }

    public boolean isMinCaracterInput(JTextField textInput, int minCaracter) {
        if (textInput.getText().length() >= minCaracter) {
            return true;
        }
        return false;
    }

    public boolean isRowSelected() {
        int rowSelected = getView().getTabelaEntidade().getSelectedRow();

        if (rowSelected == -1) {
            getView().showMessage("Selecione um(a) " + getView().getLabelEntidade());
            return false;
        }

        return true;
    }

    public int getIdSelected() {
        int linha, id;
        linha = getView().getTabelaEntidade().getSelectedRow();
        id = (Integer) getView().getTabelaEntidade().getValueAt(linha, 0);

        return id;
    }
    

    

    public abstract void carregarDadosEntidade() throws SQLException;

    public abstract void salvarEntidadeListener(String acao);
    
    protected abstract void adicionar() throws SQLException;
    protected abstract void atualizar() throws SQLException;

    class SalvarEntidadeListener implements ActionListener {

        private String acao;

        public SalvarEntidadeListener(String acao) {
            this.acao = acao;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            salvarEntidadeListener(acao);
            
        }

    }

    class AddEntidadeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dialogEntidade.limparInputs();
            dialogEntidade.setVisible(true);
            getDialogEntidade().addEntidadeListener(new SalvarEntidadeListener("Add"));

        }
    }

    class EditarEntidadeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (isRowSelected()) {

                //aqui carrega a tela com os dados da linha selecionada usando esse metodo
                try {
                    carregarDadosEntidade();
                } catch (SQLException ex) {
                    Logger.getLogger(RequisitanteController.class.getName()).log(Level.SEVERE, null, ex);
                }

                getDialogEntidade().setVisible(true);
                getDialogEntidade().addEntidadeListener(new SalvarEntidadeListener("Update"));
            }

        }
    }

    class ExcluirEntidadeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (isRowSelected()) {
                int id = getIdSelected();

                try {
                    //Exibe uma mensagem solicitando confirmação e retorna uma CONSTANTE indicando a resposta do usuario
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        entidadeDao.deletar(entidadeDao.buscarPorId(id));
                        listarEntidadesTabela();
                        getView().showMessage("Entidade excluido com sucesso");

                        return;
                    }

                } catch (SQLException ex) {
                    System.out.println("Falha ao buscar Entidade para excluir");
                }
            }
        }

    }
    
    class FiltroComboboxListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            listarEntidadesTabela();
        } catch (SQLException ex) {
            Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

}
