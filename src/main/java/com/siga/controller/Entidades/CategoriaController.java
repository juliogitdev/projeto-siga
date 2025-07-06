package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.dao.InterfaceDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Categoria;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogCategoria;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogFornecedor;
import com.siga.view.cadastros.Dialog.DialogRequisitante;
import com.siga.view.entidade.CategoriaView;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.RequisitanteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CategoriaController extends EntidadeController {

    public CategoriaController() {
        //construtor 
        super(new CategoriaView(), new CategoriaDao(), new DialogCategoria());
        super.getView().setColunasTabela(new String[]{"Id", "Nome", "Descrição"});
        super.getView().setLabelEntidade("Categoria");
    }

    @Override
    public void carregarDadosEntidade() throws SQLException {
        //aqui esse metodo basicamente vai pegar o id da linha selecionada e vai transformar em um objeto para logo apos 
        //pegarmos seus parametros para jogar na view 

        int id = getIdSelected();
        DialogCategoria dialogCategoria = (DialogCategoria) getDialogEntidade();
        CategoriaDao catDao = (CategoriaDao) getEntidadeDao();

        Categoria categoria = null;

        categoria = catDao.buscarPorId(id);

        dialogCategoria.setNome(categoria.getNome_categoria());
        dialogCategoria.setDescricao(categoria.getDescricao());

    }

    @Override
    protected void adicionar() throws SQLException {

        Categoria categoria = new Categoria();

        CategoriaDao catDao = (CategoriaDao) getEntidadeDao();
        CategoriaView ategoriaView = (CategoriaView) getView();
        DialogCategoria dialogCategoria = (DialogCategoria) getDialogEntidade();

        String inputNome, inputDescricao;

        inputNome = dialogCategoria.getNome();
        inputDescricao = dialogCategoria.getDescricao();

        //pequena verificação
        if (inputNome.isBlank()) {
            getView().showMessage("Por favor preencha os campos!");
            return;
        }

        if (catDao.buscarPorNomeExato(inputNome) == null) {
            categoria.setNome_categoria(inputNome);
            categoria.setDescricao(inputDescricao);

            //cadastrar o requisitante no banco de dados
            catDao.cadastrar(categoria);
            //atualizar tabela
            listarEntidadesTabela();
            dialogCategoria.setVisible(false);
            getView().showMessage("Requisitante Cadastrado com Sucesso!");
            return;
        }
        
        dialogCategoria.showMessage("Já existe uma categoria com este nome");

    }

    @Override
    protected void atualizar() throws SQLException {

        //vai puxar o id da linha selecionada
        int id = getIdSelected();

        CategoriaDao catDao = (CategoriaDao) getEntidadeDao();
        DialogCategoria dialogCategoria = (DialogCategoria) getDialogEntidade();

        //vai transformar o id encontrado em objeto
        Categoria categoria = catDao.buscarPorId(id);

        String inputName, inputDescricao;

        inputName = dialogCategoria.getNome();
        inputDescricao = dialogCategoria.getDescricao();

        if (inputName.isBlank()) {
            getView().showMessage("Não deixe o campo do Nome do requisitante vazio!");
            return;
        }

        if (catDao.buscarPorNomeExato(inputName).getNome_categoria().equals(categoria.getNome_categoria())
                | catDao.buscarPorNomeExato(inputName) == null) {

            //vai setar os atributos de acordo com as entradas do usuario
            categoria.setNome_categoria(inputName);
            categoria.setDescricao(inputDescricao);

            //vai atualizar no dao
            catDao.atualizar(categoria);
            listarEntidadesTabela();
            dialogCategoria.setVisible(false);
            dialogCategoria.limparInputs();
            dialogCategoria.showMessage("Categoria atualizado com sucesso!");
            return;
        }

        getDialogEntidade().showMessage("Já existe uma categoria com este nome");

    }

    @Override
    public void salvarEntidadeListener(String acao) {
        try {
            if (acao.equals("Add")) {
                adicionar();
            } else if (acao.equals("Update")) {
                atualizar();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
}
