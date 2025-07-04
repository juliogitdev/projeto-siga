package com.siga.controller.Entidades;

import com.siga.dao.CategoriaDao;
import com.siga.model.Categoria;
import com.siga.view.entidade.CategoriaView;
import com.siga.view.cadastros.Dialog.DialogCategoria;
import com.siga.view.cadastros.Dialog.DialogEntidade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Julio
 */
public class CategoriaController extends EntidadeController{
    
    public CategoriaController(){
        super(new CategoriaView(), new CategoriaDao(), new DialogCategoria());
        
        
        this.setColunasString(new String[] {"ID", "Nome", "Descrição"});
        this.setColunasTabela(this.getColunasString());

        this.getView().setLabelEntidade("Categoria");
    }
    
  


    @Override
    public void adicionarEntidade(DialogEntidade dialog) {
        
        //Verdadeiro caso o tipo de dialog seja categoria
        if(dialog instanceof DialogCategoria dialogCategoria){
            
            //Adiciona um listener no botao de salvar/cadastrar a categoria
            //Passa uma categoria vazia, com id -1, indicando que não existe no banco de dados
            dialogCategoria.addEntidadeListener(new ButtonDialogCategoriaListener(new Categoria()));
             
            
        }
        
    }

    @Override
    public void editarEntidade(DialogEntidade dialog) {
        //Pega a tablea
        JTable table = getView().getTabelaEntidade();
        
        //Pega o index da linha que está selecionada
        int indexRowEditar = table.getSelectedRow();
        
        

        try {

            //Verifico se alguma linha foi selecionada
            if(indexRowEditar > -1){
                
                //Pega o id da entidade na linha selecionada e na coluna 0 (ID)
                int idEntidadeTable = (int) table.getModel().getValueAt(indexRowEditar, 0);
                //Busca a categoria no banco de dados pelo id que pega na coluna
                Categoria categoria = (Categoria) getEntidadeDao().buscarPorId(idEntidadeTable);
                
                //Verifica se existe uma categoria no banco com esse id
                if(categoria != null){
                    //Pega o JDialog de categoria
                    DialogCategoria dCategoria = (DialogCategoria) getDialogEntidade();
                    
                    //Preenche os campos com dados da categoria
                    dCategoria.setNome(categoria.getNome_categoria());
                    dCategoria.setDescricao(categoria.getDescricao());
                    
            
                    //Deixa visivel
                    dialog.setVisible(true);
                    
                    //Adicionando listener no botão de atualizar
                    dialog.addEntidadeListener(new ButtonDialogCategoriaListener(categoria));
                }
                
            }else{
                getView().showMessage("Selecione uma categoria para editar");
            }
        } catch (SQLException ex) {
            System.out.println("Falha ao editar categoria");
        }
        
        
    }

    @Override
    public void excluirEntidade(DialogEntidade dialog) {
        JTable table = getView().getTabelaEntidade();
        int indexRow = table.getSelectedRow();
        
        //Exibe uma mensagem caso nenhum linha seja selecionada
        if(indexRow < 0){
            getView().showMessage("selecione uma categoria para excluir");
            return;
        }
        
        CategoriaDao categoriaDao = (CategoriaDao) getEntidadeDao();
        
        //Pega o id da categoria selecionada
        int idRemover = (int) table.getModel().getValueAt(indexRow, 0);
        
        try{
            //Exibe uma mensagem solicitando confirmação e retorna uma CONSTANTE indicando a resposta do usuario
            int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_OPTION);
            
            if(resposta == JOptionPane.YES_OPTION){
                categoriaDao.deletar(categoriaDao.buscarPorId(idRemover));
                listarEntidadesTabela();
                getView().showMessage("Categoria excluida com sucesso");
                return;
            }
            
            
        }catch(SQLException ex){
            System.out.println("Falha ao buscar categoria para excluir");
        }
        
        
        
        
    }
    
    
    class ButtonDialogCategoriaListener implements ActionListener{
        
        private Categoria categoria;
        private DialogCategoria dialogCategoria;
        public ButtonDialogCategoriaListener(Categoria categoria){
            this.categoria = categoria;
            this.dialogCategoria =(DialogCategoria) getDialogEntidade();
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            int id = categoria.getId();
                        
            //Verifica se categoria possui ID (ID>=0 siginifica que puxou do banco de dados e existe)
            //Se for false ele vai para o else e cria uma nova categoria
            if(id > -1){
                //Montando o objeto categoria, pois ele veio null
                categoria.setNome_categoria(dialogCategoria.getNome());
                categoria.setDescricao(dialogCategoria.getDescricao());
                
                //Exibe mensagem caso o input de nome categoria esteja vazio
                if(dialogCategoria.getNome().isEmpty()){
                    getDialogEntidade().showMessage("Por favor preencha o campo nome da categoria!");
                    return;
                }
                                
                try {
                    CategoriaDao categoriaDao = (CategoriaDao) getEntidadeDao();
                    //Busca a categoria no banco pelo nome para evitar duplicidade de categoria
                    Categoria categoriaDoBanco = categoriaDao.buscarPorNomeExato(dialogCategoria.getNome());
                    //Se a já existir uma categoria com esse nome e for diferente da que está tentando editar, exibe alerta
                    if(categoriaDoBanco != null){
                        if(categoriaDoBanco.getNome_categoria() != categoria.getNome_categoria())
                            getDialogEntidade().showMessage("Já existe uma categoria com este nome!");
                        return;
                    }else{
                        //faz o update no banco de dados passando a categoria
                        categoriaDao.atualizar(categoria);
                        //Recarrega a table de categorias
                        listarEntidadesTabela();
                        //Desmonta o dialog de cadastro
                        getDialogEntidade().setVisible(false);
                        
                        //Limpa os inputs para evitar de abrir denovo e está com os dados antigos
                        getDialogEntidade().limparInputs();
                        
                        //Exibe mensagem de sucesso
                        getView().showMessage("Categoria Editada com sucesso");
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
            //Cria categoria pois id categoria é -1, representando que não existe no banco de dados
            else{
                categoria.setNome_categoria(dialogCategoria.getNome());
                categoria.setDescricao(dialogCategoria.getDescricao());
                
                //Verificar se o usuario digitou o nome da categoria
                if(categoria.getNome_categoria().isEmpty()){
                    dialogCategoria.showMessage("Digite o nome da categoria");
                    return;
                }else{
                    try{
                        CategoriaDao categoriaDao = (CategoriaDao) getEntidadeDao();
                        
                        //Busca pela nome da categoria, se não existe retorna null
                        Categoria categoriaDoBanco = categoriaDao.buscarPorNomeExato(dialogCategoria.getNome());
                        
                        //Se a categoria não voltar null, significa que ela já existe no banco de dados
                        if(categoriaDoBanco != null){
                            dialogCategoria.showMessage("Já existe uma categoria com este nome!");
                        }else{
                            categoriaDao.cadastrar(categoria);
                            dialogCategoria.setVisible(false);
                            dialogCategoria.limparInputs();
                            listarEntidadesTabela();
                            getView().showMessage("Categoria cadastrada com sucesso!");
                        }
                    }catch(SQLException ex){
                        System.out.println("Erro ao adicionar entidade");
                    }
                    
                }
            }
            
            
        }
        
    }
    
}
