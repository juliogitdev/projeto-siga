
package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.model.Categoria;
import com.siga.view.TelaPrincipal;
import com.siga.dao.CategoriaDao;
import com.siga.model.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PrincipalController {
    
    private TelaPrincipal telaPrincipal;
    private Categoria categoria;
    private CategoriaDao categoriaDao;
    private Usuario usuario;

    public PrincipalController(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.telaPrincipal = telaPrincipal;
        telaPrincipal.setVisible(true);
        this.categoriaDao = new CategoriaDao();
        this.usuario = usuario;
        telaPrincipal.addCategoriaListener(new CategoriaListener());
    }
    
    class CategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cadastrar categoria");
        }
        
    }

    
    
    
}
