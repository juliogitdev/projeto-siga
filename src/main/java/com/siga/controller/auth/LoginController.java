package com.siga.controller.auth;

/**
 *
 * @author Julio
 */

import com.siga.controller.PrincipalController;
import com.siga.view.auth.TelaLogin;
import com.siga.dao.UsuarioDao;
import com.siga.model.Usuario;
import com.siga.util.AuthUtils;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController{
    private TelaLogin telaLogin;
    private UsuarioDao usuarioDao;

    public LoginController() {
        this.telaLogin = new TelaLogin();
        this.usuarioDao = new UsuarioDao();
        this.telaLogin.setVisible(true);
        this.telaLogin.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = telaLogin.getLoginString();
            String senha = telaLogin.getSenhaString();
        
            if(email.isEmpty() | senha.isEmpty()){
                telaLogin.showMessage("Por favor preencha todos os campos!");
                return;
            }
        
            if(!email.contains("@") | !email.contains(".")){
                telaLogin.showMessage("Por favor digite um e-mail válido");
                return;
            }
            
            Usuario user = usuarioDao.buscarPorEmail(email);
            
            if(user == null){
                telaLogin.showMessage("E-mail não cadastrado");
            }
            
            

            if(!AuthUtils.verificarSenha(senha, user.getSenha())){
                System.out.println("Senha da view: " + senha);
                System.out.println("Senha do banco " + user.getSenha());
                telaLogin.showMessage("Senha incorreta");
                return;
            }
            
            if(!user.isEnabled()){
                telaLogin.showMessage("Usuario desativado, contate um administrador ");
                return;
            }
            telaLogin.dispose();
            new PrincipalController(user);

            
        }
    }
}
    
    
     

