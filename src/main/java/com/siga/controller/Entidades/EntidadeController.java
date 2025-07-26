package com.siga.controller.Entidades;

import com.siga.controller.PrincipalController;
import com.siga.dao.InterfaceDao;
import com.siga.dao.MainDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.model.Produto;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogRequisitante;
import com.siga.view.entidade.EntidadeView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
        configurarButExcluir();
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
        List<Integer> idsAtivos = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) entidadeView.getTabelaEntidade().getModel();
       

        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).isEnabled()) {
                idsAtivos.add(entidades.get(i).getId());
            }
        }
        
        for (int linha = 0;linha< model.getRowCount(); linha++){
            int id = (Integer) model.getValueAt(linha, 0);
            if(idsAtivos.contains(id)){
               getView().adicionarLinhasColoridas(new int[]{linha}, new Color(144, 238, 144));
            }else{
                getView().adicionarLinhasColoridas(new int[]{linha}, new Color(255, 105, 97));
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
                String palavraAcao = null;
                
                Entidade entidade = null;
                Boolean entidadeAtivavel;
                
                
                try {
                    entidade = (Entidade) entidadeDao.buscarPorId(id);
                } catch (SQLException ex) {
                    Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                entidadeAtivavel = isAtivavel(entidade)
                        ;
                if(entidadeAtivavel){
                    if(entidade.isEnabled()){
                        palavraAcao = "inativar?";
                    }else{
                        palavraAcao = "ativar?";
                    }
                }else{
                    palavraAcao = "excluir?";
                }
                
                int resposta = confirmarAcao(palavraAcao);
                
                
                if(resposta == JOptionPane.YES_OPTION){
                    if(entidadeAtivavel){
                        if(entidade.isEnabled()){
                            entidade.setEnabled(false);
                        }else{
                            entidade.setEnabled(true);
                        }
                        
                        try {
                            entidadeDao.atualizar(entidade);
                        } catch (SQLException ex) {
                            Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        try {
                            entidadeDao.deletar(entidade);
                        } catch (SQLException ex) {
                            Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                try {
                    listarEntidadesTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    private boolean isAtivavel(Entidade ent){
        return(ent instanceof Produto|| ent instanceof Fornecedor || ent instanceof Requisitante);
    }
    
    private int confirmarAcao(String str){
        String frase = "Tem certeza que deseja ";
        String fraseCompleta = frase + str;
        
        int resposta = JOptionPane.showConfirmDialog(null, fraseCompleta, "Confirmar alteração", JOptionPane.YES_OPTION);
        return resposta;
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
    
     private void configurarButExcluir() {
        entidadeView.tabelaEntidadeMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = getIdSelected();
                Entidade entidade= null;
                
                try {
                    entidade = (Entidade) getEntidadeDao().buscarPorId(id);
                } catch (SQLException ex) {
                    Logger.getLogger(EntidadeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(isAtivavel(entidade)){
                    if(entidade.isEnabled()){
                        entidadeView.setButExcluirText("INATIVAR");
                    }else{
                        entidadeView.setButExcluirText("ATIVAR");
                    }
                
                }else{
                    entidadeView.setButExcluirText("EXCLUIR");
                }
               
            }
             
        
        });
    }
            
            
    
  

}
