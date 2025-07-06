package com.siga.controller.Entidades;

import com.siga.dao.FornecedorDao;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import com.siga.view.cadastros.Dialog.DialogFornecedor;
import com.siga.view.entidade.FornecedorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Julio
 */

public class FornecedorController{
    /**
    public FornecedorController() {
        super(new FornecedorView(), new FornecedorDao(), new DialogFornecedor());

        //Seta o nome das colunas
        getView().setColunasTabela(new String[]{"ID", "Razão Social", "CNPJ", "E-mail", "Telefone", "Endereço"});

        //Troca o label e coloca "Fornecedor"
        getView().setLabelEntidade("Fornecedor");

    }

    @Override
    public void adicionarEntidade(DialogEntidade dialog) {
        DialogFornecedor dialogFornecedor = (DialogFornecedor) dialog;
        dialogFornecedor.addEntidadeListener(new SalvarButtonListener(new Fornecedor()));

        System.out.println("Cliando em adicionar");
    }

    @Override
    public void editarEntidade(DialogEntidade dialog) {
        DialogFornecedor dialogFornecedor = (DialogFornecedor) dialog;
        FornecedorDao fd = (FornecedorDao) getEntidadeDao();
        //Chama o método de verificar se foi selecionado uma entidade, se for true ele prossegue;
        if (isRowSelected()) {
            System.out.println("Clicando em editar");
            int rowSelected = getView().getTabelaEntidade().getSelectedRow();
            int idFornecedorBanco = (int) getView().getTabelaEntidade().getModel().getValueAt(rowSelected, 0);
            Fornecedor f;
            try {
                f = fd.buscarPorId(idFornecedorBanco);
                dialogFornecedor.setRazaoSocialText(f.getRazaoSocial());
                dialogFornecedor.setCnpjText(f.getCnpj());
                dialogFornecedor.setTelefoneText(f.getTelefone());
                dialogFornecedor.setEmailText(f.getEmail());
                dialogFornecedor.setEnderecoText(f.getEndereco());
                getDialogEntidade().setVisible(true);
                dialog.addEntidadeListener(new SalvarButtonListener(f));
            } catch (SQLException ex) {
                Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void excluirEntidade(DialogEntidade dialog) {
        JTable table = getView().getTabelaEntidade();
        int indexRow = table.getSelectedRow();

        //Exibe uma mensagem caso nenhum linha seja selecionada
        if (indexRow < 0) {
            getView().showMessage("selecione uma categoria para excluir");
            return;
        }

        FornecedorDao fornecedorDao = (FornecedorDao) getEntidadeDao();

        //Pega o id da categoria selecionada
        int idRemover = (int) table.getModel().getValueAt(indexRow, 0);

        try {
            //Exibe uma mensagem solicitando confirmação e retorna uma CONSTANTE indicando a resposta do usuario
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                fornecedorDao.deletar(fornecedorDao.buscarPorId(idRemover));
                listarEntidadesTabela();
                getView().showMessage("Fornecedor excluido com sucesso");
                return;
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar categoria para excluir");
        }

    }

    class SalvarButtonListener implements ActionListener {

        private Fornecedor fornecedor;
        private DialogFornecedor dialogFornecedor;

        public SalvarButtonListener(Fornecedor fornecedor) {
            this.dialogFornecedor = (DialogFornecedor) getDialogEntidade();
            this.fornecedor = fornecedor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            FornecedorDao fd = (FornecedorDao) getEntidadeDao();
            //id -1 significa que foi criado pelo sistema e não que veio do banco de dados
            if (fornecedor.getId() == -1) {
                String cnpj = dialogFornecedor.getCnpjInput().getText();

                try {
                    Fornecedor fornecedorBD = fd.buscarPorCnpj(cnpj);

                    if (dialogFornecedor.getCnpjInput().getText().isBlank()
                            || dialogFornecedor.getEmailInput().getText().isBlank()) {
                        dialogFornecedor.showMessage("Por favor preencha os campos");
                        return;
                    } else {
                        //Se não exister fornecedor no banco com esse cnpj...
                        if (fornecedorBD == null) {
                            String razaoSocial = dialogFornecedor.getRazaoSocialInput().getText();
                            String telefone = dialogFornecedor.getTelefoneInput().getText();
                            String endereco = dialogFornecedor.getEnderecoInput().getText();
                            String email = dialogFornecedor.getEmailInput().getText();

                            Fornecedor fornecedorAtualizado = new Fornecedor(razaoSocial, cnpj, telefone, endereco, email);
                            fornecedorAtualizado.setId(fornecedor.getId());

                            fd.cadastrar(fornecedorAtualizado);

                            listarEntidadesTabela();

                            dialogFornecedor.setVisible(false);
                            dialogFornecedor.limparInputs();

                        } else {
                            dialogFornecedor.showMessage("Já existe um fornecedor com este cnpj");
                            return;
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            } else {

                DialogFornecedor dialogF = (DialogFornecedor) getDialogEntidade();
                String cnpjNovo = (String) dialogF.getCnpjInput().getText();

                if (cnpjNovo.isBlank()) {
                    dialogF.showMessage("Cnpj não pode ser vazio");

                } else {
                    try {
                        Fornecedor fornecedorBD = fd.buscarPorCnpj(dialogFornecedor.getCnpjInput().getText());
                        

                        
                        
                        if (fornecedorBD == null | fornecedor.getCnpj().equals(cnpjNovo)) {
                            String razaoSocial = dialogFornecedor.getRazaoSocialInput().getText();
                            String telefone = dialogFornecedor.getTelefoneInput().getText();
                            String endereco = dialogFornecedor.getEnderecoInput().getText();
                            String email = dialogFornecedor.getEmailInput().getText();

                            Fornecedor fAtualizado = new Fornecedor(razaoSocial, cnpjNovo, telefone, endereco, email);
                            fAtualizado.setId(fornecedor.getId());

                            fd.atualizar(fAtualizado);
                            listarEntidadesTabela();

                            dialogF.setVisible(false);
                            getView().showMessage("Fornecedor atualizado");

                        } else {
                            dialogF.showMessage("Este cnpj já está cadastrado");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }
**/
}
