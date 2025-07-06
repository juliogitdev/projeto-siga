package com.siga.controller.Entidades;

import com.siga.dao.InterfaceDao;
import com.siga.dao.RequisitanteDao;
import com.siga.model.Requisitante;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogFornecedor;
import com.siga.view.cadastros.Dialog.DialogRequisitante;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.RequisitanteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RequisitanteController extends EntidadeController {

    public RequisitanteController() {
        //construtor 
        super(new RequisitanteView(), new RequisitanteDao(), new DialogRequisitante());
        super.getView().setColunasTabela(new String[]{"Id", "Nome", "Setor", "Endereço"});
        super.getView().setLabelEntidade("Requisitante");
    }

    @Override
    public void carregarDadosEntidade() throws SQLException {
        //aqui esse metodo basicamente vai pegar o id da linha selecionada e vai transformar em um objeto para logo apos 
        //pegarmos seus parametros para jogar na view 

        
        int id = getIdSelected();
        DialogRequisitante dialogRequisitante = (DialogRequisitante) getDialogEntidade();
        RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
        

        Requisitante requisitante = null;

        requisitante = reqDao.buscarPorId(id);

        dialogRequisitante.setNomeInput(requisitante.getNome());
        dialogRequisitante.setSetorInput(requisitante.getSetor());
        dialogRequisitante.setEnderecoInput(requisitante.getEndereco());
    }

    @Override
    protected void adicionar() throws SQLException {

        Requisitante requisitante = new Requisitante();

        RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
        RequisitanteView requisitanteView = (RequisitanteView) getView();
        DialogRequisitante dialogRequisitante = (DialogRequisitante) getDialogEntidade();

        String inputNome, inputSetor, inputEndereco;

        inputNome = dialogRequisitante.getNomeInput().getText();
        inputSetor = dialogRequisitante.getSetorInput().getText();
        inputEndereco = dialogRequisitante.getEnderecoInput().getText();

        System.out.println(inputNome + inputSetor + inputEndereco);
        //pequena verificação
        if (inputNome.isBlank() || inputSetor.isBlank()) {
            requisitanteView.showMessage("Por favor preencha os campos!");
            return;
        }
        
        

        requisitante.setNome(inputNome);
        requisitante.setSetor(inputSetor);
        requisitante.setEndereco(inputEndereco);
        //cadastrar o requisitante no banco de dados
        reqDao.cadastrar(requisitante);
        //atualizar tabela
        listarEntidadesTabela();
        dialogRequisitante.setVisible(false);
        requisitanteView.showMessage("Requisitante Cadastrado com Sucesso!");

    }
    @Override
    protected void atualizar() throws SQLException {

        //vai puxar o id da linha selecionada
        int id = getIdSelected();

        System.out.println("atualizando");
        RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
        DialogRequisitante dialogRequisitante = (DialogRequisitante) getDialogEntidade();

        //vai transformar o id encontrado em objeto
        Requisitante requisitante = reqDao.buscarPorId(id);

        String inputName, inputSetor, inputEndereco;

        inputName = dialogRequisitante.getNomeInput().getText();
        inputSetor = dialogRequisitante.getSetorInput().getText();
        inputEndereco = dialogRequisitante.getEnderecoInput().getText();

        if (inputName.isBlank()) {
            dialogRequisitante.showMessage("Não deixe o campo do Nome do requisitante vazio!");
            return;
        }

        //vai setar os atributos de acordo com as entradas do usuario
        requisitante.setNome(inputName);
        requisitante.setSetor(inputSetor);
        requisitante.setEndereco(inputEndereco);
        //vai atualizar no dao
        reqDao.atualizar(requisitante);
        listarEntidadesTabela();
        dialogRequisitante.setVisible(false);
        dialogRequisitante.limparInputs();
        dialogRequisitante.showMessage("Requisitante atualizado com sucesso!");

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
