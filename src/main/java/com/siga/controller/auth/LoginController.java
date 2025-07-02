package com.siga.controller.auth;

/**
 *
 * @author Julio
 */

import com.siga.view.auth.TelaLogin;
import com.siga.dao.UsuarioDao;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController{
    private TelaLogin telaLogin;
    private UsuarioDao usuarioDao;

    public LoginController(TelaLogin tl, UsuarioDao ud) {
        this.telaLogin = tl;
        this.usuarioDao = ud;
        tl.setVisible(true);
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
        
        }
    }
}
    
    
     

