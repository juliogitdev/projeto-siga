package com.siga.controller.Entidades;

import com.siga.dao.FornecedorDao;
import com.siga.dao.InterfaceDao;
import com.siga.model.Fornecedor;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogFornecedor;
import com.siga.view.entidade.EntidadeView;
import com.siga.view.entidade.FornecedorView;
import java.sql.SQLException;

public class FornecedorController extends EntidadeController {

    public FornecedorController() {
        super(new FornecedorView(), new FornecedorDao(), new DialogFornecedor());
        super.getView().setColunasTabela(new String[]{"Id", "Razão Social", "CNPJ", "Email", "Telefone", "Endereço"});
        super.getView().setLabelEntidade("Fornecedor");
    }

    @Override
    public void carregarDadosEntidade() throws SQLException {
        int id = getIdSelected();
        DialogFornecedor dialogFornecedor = (DialogFornecedor) getDialogEntidade();
        FornecedorDao fornDao = (FornecedorDao) getEntidadeDao();

        Fornecedor fornecedor = null;
        fornecedor = fornDao.buscarPorId(id);

        dialogFornecedor.setRazaoSocialText(fornecedor.getRazaoSocial());
        dialogFornecedor.setCnpjText(fornecedor.getCnpj());
        dialogFornecedor.setEnderecoText(fornecedor.getEndereco());
        dialogFornecedor.setEmailText(fornecedor.getEmail());
        dialogFornecedor.setTelefoneText(fornecedor.getTelefone());

    }
    
    @Override
    protected void adicionar() throws SQLException {
        DialogFornecedor dialogFornecedor = (DialogFornecedor) getDialogEntidade();
        FornecedorDao fornDao = (FornecedorDao) getEntidadeDao();
        FornecedorView fornView = (FornecedorView) getView();

        Fornecedor fornecedor = new Fornecedor();

        String inputRazaoSocial, inputCnpj, inputEndereco, inputTelefone, inputEmail;

        inputRazaoSocial = dialogFornecedor.getRazaoSocialInput().getText();
        inputCnpj = dialogFornecedor.getCnpjInput().getText();
        inputEndereco = dialogFornecedor.getEnderecoInput().getText();
        inputEmail = dialogFornecedor.getEmailInput().getText();
        inputTelefone = dialogFornecedor.getTelefoneInput().getText();

        fornecedor.setRazaoSocial(inputRazaoSocial);
        fornecedor.setCnpj(inputCnpj);
        fornecedor.setEndereco(inputEndereco);
        fornecedor.setEmail(inputEmail);
        fornecedor.setTelefone(inputTelefone);

        fornDao.cadastrar(fornecedor);
        dialogFornecedor.limparInputs();
        listarEntidadesTabela();
        dialogFornecedor.setVisible(false);
        fornView.showMessage("Fornecedor Cadastrado com Sucesso!");
    }

    @Override
    protected void atualizar() throws SQLException {

        int id = getIdSelected();

        FornecedorDao fornDao = (FornecedorDao) getEntidadeDao();
        DialogFornecedor dialogFornecedor = (DialogFornecedor) getDialogEntidade();

        Fornecedor fornecedor = fornDao.buscarPorId(id);

        String inputRazaoSocial, inputCnpj, inputEndereco, inputTelefone, inputEmail;

        inputRazaoSocial = dialogFornecedor.getRazaoSocialInput().getText();
        inputCnpj = dialogFornecedor.getCnpjInput().getText();
        inputEndereco = dialogFornecedor.getEnderecoInput().getText();
        inputEmail = dialogFornecedor.getEmailInput().getText();
        inputTelefone = dialogFornecedor.getTelefoneInput().getText();

        fornecedor.setRazaoSocial(inputRazaoSocial);
        fornecedor.setCnpj(inputCnpj);
        fornecedor.setEndereco(inputEndereco);
        fornecedor.setEmail(inputEmail);
        fornecedor.setTelefone(inputTelefone);

        fornDao.atualizar(fornecedor);
        listarEntidadesTabela();
        dialogFornecedor.setVisible(false);
        dialogFornecedor.limparInputs();
        dialogFornecedor.showMessage("Fornecedor atualizado com sucesso!");

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


