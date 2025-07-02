package com.siga.controller.Entidades;

import com.siga.dao.FornecedorDao;
import com.siga.model.Entidade;
import com.siga.model.Fornecedor;
import com.siga.view.cadastros.Dialog.DialogFornecedor;
import com.siga.view.entidade.FornecedorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Julio
 */
public class FornecedorController {
    private FornecedorView fornecedorView;
    private FornecedorDao fornecedorDao;
    private DialogFornecedor fornecedorDialog;
    private String[] colunasString = {"Razão Social", "CNPJ", "E-mail", "Telefone", "Endereço"};

    public FornecedorController() {
        this.fornecedorView = new FornecedorView();
        this.fornecedorDao = new FornecedorDao();
        this.fornecedorDialog = new DialogFornecedor();
        
        //Seta o nome das colunas
        fornecedorView.setColunasTabela(colunasString);
        
        //Troca o label e coloca "Fornecedor"
        fornecedorView.setLabelEntidade("Fornecedor");
        
        //Adiciona o listeners nos botões
        fornecedorView.addEntidadeListenner(new AddEntidade());
        fornecedorView.editarEntidadeListener(new EditarEntidade());
        fornecedorView.excluirEntidadeListener(new ExcluirEntidade());
    }
    
    //retorna a view do fornecedor
    public FornecedorView getView(){
        return fornecedorView;
    }
    
    //carrega a tabela de fornecedores
    public void carregarFornecedores() throws SQLException{
        List<Fornecedor> fornecedores = fornecedorDao.listarTodos();
        //chama o método de atualizar tabela da view de fornecedores
        fornecedorView.atualizarTabela(fornecedores);
    }
    
    
    //classe para manipular quando o botão de adicionar fornecedor é clicado
    class AddEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em adicionar");
        }
        
    }
    
    //classe para manipular quando o botão de editar fornecedor é clicado
    class EditarEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em editar");
        }   
    }
    //classe para manipular quando o botão de excluir fornecedor é clicado
    class ExcluirEntidade implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clicando em excluir");
        }   
    }
    
}
