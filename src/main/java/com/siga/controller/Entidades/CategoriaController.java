/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.model.Categoria;
import com.siga.model.Fornecedor;
import com.siga.view.entidade.CategoriaView;
import com.siga.view.cadastros.Dialog.DialogCategoria;
import com.siga.view.entidade.FornecedorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Julio
 */
public class CategoriaController {
    private CategoriaView categoriaView;
    private CategoriaDao categoriaDao;
    private DialogCategoria dialogCategoria;
    private String[] colunasString = {"Nome", "Descrição"};

    public CategoriaController() {
        this.categoriaView = new CategoriaView();
        this.categoriaDao = new CategoriaDao();
        this.dialogCategoria = new DialogCategoria();
        
        categoriaView.setColunasTabela(colunasString);
        categoriaView.setLabelEntidade("Categoria");
        
        categoriaView.addEntidadeListenner(new AddEntidade());
        categoriaView.editarEntidadeListener(new EditarEntidade());
        categoriaView.excluirEntidadeListener(new ExcluirEntidade());
    }
    
    
    
    
    
    
    //retorna a view do categoria
    public CategoriaView getView(){
        return categoriaView;
    }
    
    //carrega a tabela de categoria
    public void carregarCategorias() throws SQLException{
        List<Categoria> categorias = categoriaDao.listarTodos();
        //chama o método de atualizar tabela da view de fornecedores
        categoriaView.atualizarTabela(categorias);
    }
    
    
    //classe para manipular quando o botão de adicionar categoria é clicado
    class AddEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em adicionar");
        }
        
    }
    
    //classe para manipular quando o botão de editar categoria é clicado
    class EditarEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em editar");
        }   
    }
    //classe para manipular quando o botão de excluir categoria é clicado
    class ExcluirEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em excluir");
        }   
    }
}
