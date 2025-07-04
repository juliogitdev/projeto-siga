
package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.controller.Entidades.CategoriaController;
import com.siga.controller.Entidades.FornecedorController;
import com.siga.view.TelaPrincipal;
import com.siga.model.Usuario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrincipalController {
    
    private TelaPrincipal telaPrincipal;
    private CategoriaController categoriaController;
    private FornecedorController fornecedorController;
    private Usuario usuario;

    public PrincipalController(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.telaPrincipal = telaPrincipal;
        
        //Deixa a tela principal visivel
        telaPrincipal.setVisible(true);
        telaPrincipal.setLabelUsuario(usuario.getNome());
        
        this.categoriaController = new CategoriaController();
        this.fornecedorController = new FornecedorController();
        this.usuario = usuario;
        
        //Adicionando listeners nas entidades do menu
        telaPrincipal.CategoriaListener(new CategoriaListener());
        telaPrincipal.FornecedorListener(new FornecedorListener());
    }
    
    class CategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(categoriaController.getView());
            try {
                categoriaController.carregarCategorias();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
