package com.siga.controller.Entidades;

import com.siga.dao.InterfaceDao;
import com.siga.dao.MainDao;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.entidade.EntidadeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTextField;
/**
 *
 * @author Julio
 */
public abstract class EntidadeController{
    
    private String[] colunasString = {};
    private EntidadeView entidadeView;
    private InterfaceDao entidadeDao;
    private DialogEntidade dialogEntidade;

    public EntidadeController(EntidadeView entidadeView, InterfaceDao entidadeDao, DialogEntidade dial) {
        this.entidadeView = entidadeView;
        this.entidadeDao =(InterfaceDao) entidadeDao;
        this.dialogEntidade = dial;
        
        
        entidadeView.addEntidadeListenner(new AddEntidadeListener());
        entidadeView.editarEntidadeListener(new EditarEntidadeListener());
        entidadeView.excluirEntidadeListener(new ExcluirEntidadeListener());
    }
    
    public List<InterfaceDao> getTodasEntidades() throws SQLException{
        return getEntidadeDao().listarTodos();
    }

    public String[] getColunasString() {
        return colunasString;
    }

    public void setColunasString(String[] colunasString) {
        this.colunasString = colunasString;
    }

    public void setColunasTabela(String[] colunas){
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
    
    public void listarEntidadesTabela() throws SQLException{
        getView().atualizarTabela(getEntidadeDao().listarTodos());
    }
    
    public void setVisibleDialog(){
        getDialogEntidade().setVisible(true);
    }
    
    public boolean isInputEmpty(JTextField textInput){
        return textInput.getText().isBlank();
    }
    
    public boolean isMinCaracterInput(JTextField textInput, int minCaracter){
        if(textInput.getText().length() >= minCaracter){
            return true;
        }
        return false;
    }
    
    public abstract void adicionarEntidade(DialogEntidade dialog);
    public abstract void editarEntidade(DialogEntidade dialog);
    public abstract void excluirEntidade(DialogEntidade dialog);
    
    public boolean isRowSelected(){
        int rowSelected = getView().getTabelaEntidade().getSelectedRow();
                
        if(rowSelected == -1){
            getView().showMessage("Selecione um(a) " + getView().getLabelEntidade());
            return false;
        }
        
        return true;
    }
    
    class AddEntidadeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dialogEntidade.limparInputs();
            dialogEntidade.setVisible(true);
            adicionarEntidade(dialogEntidade);

        }
    }
    
    class EditarEntidadeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            editarEntidade(dialogEntidade);
        }
    }
    
    class ExcluirEntidadeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            excluirEntidade(dialogEntidade);
        }
    }
    
}
