
package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.controller.Entidades.CategoriaController;
import com.siga.controller.Entidades.FornecedorController;
import com.siga.view.TelaPrincipal;
import com.siga.dao.CategoriaDao;
import com.siga.model.Usuario;
import com.siga.view.cadastros.Dialog.DialogAddCategoria;
import com.siga.view.entidade.CategoriaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



public class PrincipalController {
    
    private TelaPrincipal telaPrincipal;
    private CategoriaController cadCategoriaController;
    private FornecedorController fornecedorController;
    private Usuario usuario;

    public PrincipalController(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.telaPrincipal = telaPrincipal;
        
        //Deixa a tela principal visivel
        telaPrincipal.setVisible(true);
        telaPrincipal.setLabelUsuario(usuario.getNome());
        
        this.cadCategoriaController = new CategoriaController(new CategoriaView(), new CategoriaDao(), new DialogAddCategoria());
        this.fornecedorController = new FornecedorController();
        this.usuario = usuario;
        
        //Adicionando listeners nas entidades do menu
        telaPrincipal.CategoriaListener(new CategoriaListener());
        telaPrincipal.FornecedorListener(new FornecedorListener());
    }
    
    class CategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(cadCategoriaController.getView());
            cadCategoriaController.CarregarCategorias();
        }
        
    }
    
    class FornecedorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(fornecedorController.getView());
            try {
                fornecedorController.carregarFornecedores();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }

    
    
    
}
