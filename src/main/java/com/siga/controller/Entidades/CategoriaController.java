/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.model.Categoria;
import com.siga.view.entidade.CategoriaView;
import com.siga.view.cadastros.Dialog.DialogAddCategoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class CategoriaController {
    private CategoriaView cadastrarCategoriaView;
    private CategoriaDao categoriaDao;
    private DialogAddCategoria addDialogCategoria;
    public CategoriaController(CategoriaView cadastrarCategoriaView, CategoriaDao categoriaDao, DialogAddCategoria addDialogCategoria){
        this.cadastrarCategoriaView = cadastrarCategoriaView;
        this.categoriaDao = categoriaDao;
        this.addDialogCategoria = addDialogCategoria;
        
        cadastrarCategoriaView.AddCategoriaListenner(new AddCategoriaListener());
        cadastrarCategoriaView.editarCategoriaListener(new editarCategoriaListener());
        cadastrarCategoriaView.excluirCategoriaListener(new excluirCategoriaListener());
        
        addDialogCategoria.addDialogCategoria(new AddDialogCategoriaListener());
        
    }
    
    
    
    public CategoriaView getView(){
        return cadastrarCategoriaView;
    }
    
    public List<Categoria> buscarCategorias() throws SQLException{
       return categoriaDao.listarTodos();
    }
    
    public void CarregarCategorias(){
        try{
            List<Categoria> categorias = buscarCategorias();
            cadastrarCategoriaView.atualizarTabela(categorias);
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    
    class AddCategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            addDialogCategoria.setVisible(true);
        }
    }
    
    class AddDialogCategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = addDialogCategoria.getNomeInput().getText();
            String descricao = addDialogCategoria.getDescricaoInput().getText();
            
            try {
                Categoria c = categoriaDao.buscarPorNomeExato(nome);
                if(c == null){
                    if(nome.isEmpty() | nome == ""){
                        addDialogCategoria.showMessage("Campo não pode está vázio!");
                    }else{
                        categoriaDao.cadastrar(new Categoria(nome, descricao));
                        addDialogCategoria.setVisible(false);
                        CarregarCategorias();
                    }
                    
                }else{
                    addDialogCategoria.showMessage("Já existe uma categoria com esse nome");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
    class editarCategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em editar");
        }
        
    }
    
    class excluirCategoriaListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Excluido categoria");
        }
        
    }
    
    

    
    
    
}
