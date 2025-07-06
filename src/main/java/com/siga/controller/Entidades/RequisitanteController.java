
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




public class RequisitanteController extends EntidadeController{

    public RequisitanteController() {
        //construtor 
        super( new RequisitanteView(),  new RequisitanteDao(),  new DialogRequisitante());
        super.getView().setColunasTabela(new String[] {"Id", "Nome", "Setor", "Endereço"});
        super.getView().setLabelEntidade("Requisitante");
    }

    @Override
    public void adicionarEntidade(DialogEntidade dialog) {
        //aqui passa o parametro "add" para o listener para ele saber o que tem que fazer
        DialogRequisitante dialogRequisitante = (DialogRequisitante) dialog;
        dialogRequisitante.addEntidadeListener(new SalvarButtonListener("Add"));    
    }
    
    @Override
    public void editarEntidade(DialogEntidade dialog) {
        //aqui carrega a tela com os dados da linha selecionada usando esse metodo
        try {
            carregarDadosRequisitante();
        } catch (SQLException ex) {
            Logger.getLogger(RequisitanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //aqui torna a tela visivel e passa o update como parametro pra ele saber o que fazer
        DialogRequisitante dialogRequisitante = (DialogRequisitante) dialog;
        dialogRequisitante.setVisible(true);
        dialogRequisitante.addEntidadeListener(new SalvarButtonListener("Update")); 
    }
    
    @Override
    public void excluirEntidade(DialogEntidade dialog) {
        //esse foi copia e cola rs
        RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
        
        if(isRowSelected()){
            int id = getIdSelected();
            
             try {
            //Exibe uma mensagem solicitando confirmação e retorna uma CONSTANTE indicando a resposta do usuario
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                reqDao.deletar(reqDao.buscarPorId(id));
                listarEntidadesTabela();
                getView().showMessage("Fornecedor excluido com sucesso");
                return;
            }

        } catch (SQLException ex) {
            System.out.println("Falha ao buscar categoria para excluir");
        }
        
        }
    }
    
    private void carregarDadosRequisitante() throws SQLException{
        //aqui esse metodo basicamente vai pegar o id da linha selecionada e vai transformar em um objeto para logo apos 
        //pegarmos seus parametros para jogar na view 
        
        DialogRequisitante dialogRequisitante = (DialogRequisitante) getDialogEntidade();
            RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
            
            Requisitante requisitante = null;
            
            if(isRowSelected()){
               int id = getIdSelected();
               requisitante = reqDao.buscarPorId(id);
               
               dialogRequisitante.setNomeInput(requisitante.getNome());
               dialogRequisitante.setSetorInput(requisitante.getSetor());
               dialogRequisitante.setEnderecoInput(requisitante.getEndereco());
            }
        }
    
    class SalvarButtonListener implements ActionListener{
        private DialogRequisitante dialogRequisitante;
        private String acao;
        
        public SalvarButtonListener(String acao){
            this.acao = acao;
            this.dialogRequisitante = (DialogRequisitante) getDialogEntidade();
        
        }
        //aqui eu fiz a adicionar requisitante
        private void adicionar() throws SQLException{
            
            Requisitante requisitante = new Requisitante();
            
            RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
            RequisitanteView requisitanteView = (RequisitanteView) getView();
            
            String inputNome,inputSetor, inputEndereco;
            
            inputNome = dialogRequisitante.getNomeInput().getText();
            inputSetor = dialogRequisitante.getSetorInput().getText();
            inputEndereco = dialogRequisitante.getEnderecoInput().getText();
            
            System.out.println(inputNome + inputSetor + inputEndereco);
            //pequena verificação
            if(inputNome.isBlank() || inputEndereco.isBlank() || inputSetor.isBlank()){
                requisitanteView.showMessage("PREENCHA TODOS OS CAMPOS, PORRA!");
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
        
        private void atualizar() throws SQLException{
            
            //vai puxar o id da linha selecionada
            int id = getIdSelected();
            
            System.out.println("atualizando");
            RequisitanteDao reqDao = (RequisitanteDao) getEntidadeDao();
            
            //vai transformar o id encontrado em objeto
            Requisitante requisitante = reqDao.buscarPorId(id);
            
            String inputName, inputSetor, inputEndereco;
            
            inputName = dialogRequisitante.getNomeInput().getText();
            inputSetor = dialogRequisitante.getSetorInput().getText();
            inputEndereco = dialogRequisitante.getEnderecoInput().getText();
            
            if(inputName.isBlank()){
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
        public void actionPerformed(ActionEvent e) {
           switch(acao){
               case "Add" ->                {
                   try {
                       adicionar();
                   } catch (SQLException ex) {
                       Logger.getLogger(RequisitanteController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }

               case "Update" -> {
                   try {
                       atualizar();
                   } catch (SQLException ex) {
                       Logger.getLogger(RequisitanteController.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
            
        }
    }
}
