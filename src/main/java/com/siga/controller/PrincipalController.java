
package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.controller.Entidades.CategoriaController;
import com.siga.controller.Entidades.FornecedorController;
import com.siga.controller.Entidades.MovimentacaoController;
import com.siga.controller.Entidades.ProdutoController;
import com.siga.controller.Entidades.RequisitanteController;
import com.siga.controller.RelatorioController;
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
    private RequisitanteController requisitanteController;
    private ProdutoController produtoController;
    private MovimentacaoController movimentacaoController;
    private PerfilController perfilController;
    private RelatorioController relatorioController;
    private Usuario usuario;

   
    public PrincipalController(TelaPrincipal telaPrincipal, Usuario usuario) {
        this.telaPrincipal = telaPrincipal;
        this.usuario = usuario;
        this.perfilController = new PerfilController(telaPrincipal, usuario);
        this.categoriaController = new CategoriaController();
        this.fornecedorController = new FornecedorController();
        this.requisitanteController = new RequisitanteController();
        this.produtoController = new ProdutoController();
        this.relatorioController = new RelatorioController();
        this.movimentacaoController = new MovimentacaoController(usuario);
        
        //Adicionando listeners nas entidades do menu
        telaPrincipal.CategoriaListener(new CategoriaListener());
        telaPrincipal.FornecedorListener(new FornecedorListener());
        telaPrincipal.RequisitanteListener(new RequisitanteListener());
        telaPrincipal.ProdutoListener(new ProdutoListener());
        telaPrincipal.MovimentacaoListener(new MovimentacaoListener());
        telaPrincipal.perfilListener(new EditarPerfilListener());
        telaPrincipal.relatorioButtonListener(new RelatorioListener());
        viewAdmin();
        
        //Deixa a tela principal visivel
        telaPrincipal.setLabelUsuario(usuario.getNome());
        telaPrincipal.setVisible(true);
        
    }
    
    public void viewAdmin(){
        Usuario u = getUsuario();
        System.out.println(u.getNivelAcesso());
        if(u.getNivelAcesso() == 1){
            telaPrincipal.setVisibleViewAdmin(true);
            return;
        }
        
        telaPrincipal.setVisibleViewAdmin(false);
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    class CategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(categoriaController.getView());
            try {
                categoriaController.listarEntidadesTabela();
                
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
                fornecedorController.listarEntidadesTabela();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
    class RequisitanteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(requisitanteController.getView());
            try {
                 requisitanteController.listarEntidadesTabela();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
    class ProdutoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(produtoController.getView());
            
            try{
                
                produtoController.listarEntidadesTabela();
                produtoController.carregarComboBoxsDialog();
                
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        
    }

    class MovimentacaoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            telaPrincipal.setContentPanel(movimentacaoController.getView());
            
            try{
                
                movimentacaoController.listarEntidadesTabela();
                
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }
        
    }
    
    class EditarPerfilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            perfilController.iniciar();
            telaPrincipal.setLabelUsuario(perfilController.getUsuario().getNome());
        }
        
    }
    
    class RelatorioListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            relatorioController.setVisible(true);
        }
        
    }
    
}
