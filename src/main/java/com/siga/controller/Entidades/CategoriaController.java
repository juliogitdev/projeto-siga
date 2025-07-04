package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.model.Categoria;
import com.siga.view.entidade.CategoriaView;
import com.siga.view.cadastros.Dialog.DialogCategoria;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Julio
 */
public class CategoriaController extends EntidadeController{
    
    public CategoriaController(){
        super(new CategoriaView(), new CategoriaDao(), new DialogCategoria());
        this.setColunasString(new String[] {"Nome", "Descrição"});
        this.setColunasTabela(this.getColunasString());
        
        getDialogEntidade().addEntidadeListener(new ButtonAddCategoriaListener());
        
        this.getView().setLabelEntidade("Categoria");
    }


    @Override
    public void adicionarEntidade(DialogEntidade dialog) {
        if(dialog instanceof DialogCategoria dialogCategoria){
            dialogCategoria.addEntidadeListener(new ButtonAddCategoriaListener());
             
            
        }
        
    }

    @Override
    public void editarEntidade(DialogEntidade dialog) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluirEntidade(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    class ButtonAddCategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando");
        }
        
    }
    
}
