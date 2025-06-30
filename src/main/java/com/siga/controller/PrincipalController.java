
package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.model.Categoria;
import com.siga.view.TelaPrincipal;
import com.siga.dao.CategoriaDao;
import com.siga.model.Usuario;
import com.siga.view.cadastros.CadastrarCategoria;
import com.siga.view.cadastros.Dialog.DialogAddCategoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrincipalController {
    
    private TelaPrincipal telaPrincipal;
    private CadastrarCategoriaController cadCategoriaController;
    private Usuario usuario;

    public PrincipalController(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.telaPrincipal = telaPrincipal;
        
        telaPrincipal.setVisible(true);
        telaPrincipal.setLabelUsuario(usuario.getNome());
        
        this.cadCategoriaController = new CadastrarCategoriaController(new CadastrarCategoria(), new CategoriaDao(), new DialogAddCategoria());
        
        this.usuario = usuario;
        telaPrincipal.CategoriaListener(new CategoriaListener());
    }
    
    class CategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(cadCategoriaController.getView());
            cadCategoriaController.CarregarCategorias();
        }
        
    }

    
    
    
}
