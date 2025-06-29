package com.siga.controller;

/**
 *
 * @author Julio
 */

import com.siga.dao.UsuarioDao;
import com.siga.model.Usuario;
import com.siga.view.auth.TelaRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RegistroController {
    
    private UsuarioDao usuarioDao;
    private TelaRegistro telaRegistro;
    
    public RegistroController(TelaRegistro telaRegistro, UsuarioDao usuarioDao){
        this.telaRegistro = telaRegistro;
        telaRegistro.setVisible(true);
        this.usuarioDao = usuarioDao;
        telaRegistro.addRegistroListener(new RegistroListener());
    }
    
    class RegistroListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String nome = telaRegistro.getNomeString();
                String email = telaRegistro.getEmailString();
                String senha = telaRegistro.getSenhaString();
                
                if(nome.isEmpty() | email.isEmpty() | senha.isEmpty()){
                    telaRegistro.showMessage("Por favor preencha todos os campos!");
                    return;
                }
                
                if(nome.length() < 5){
                    telaRegistro.showMessage("Digite seu nome completo!");
                    return;
                }
                
                if(!email.contains("@") | !email.contains(".")){
                    telaRegistro.showMessage("Digite um e-mail válido!");
                    return;
                }
                
                if(senha.length() < 8){
                    telaRegistro.showMessage("Digite uma senha maior");
                    return;
                }
                
                if(usuarioDao.buscarPorEmail(email) != null){
                    telaRegistro.showMessage("E-mail já cadastrado");
                    return;
                }
                
                Usuario user = new Usuario(nome, email, senha);
                usuarioDao.cadastrar(user);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   
        
    }
    
}
