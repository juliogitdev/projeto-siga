package com.siga;

import com.siga.controller.LoginController;
import com.siga.dao.UsuarioDao;
import com.siga.view.auth.TelaLogin;
import java.sql.SQLException;

public class Main {
    public static void main (String args[]) throws SQLException{
        TelaLogin tl = new TelaLogin();
        UsuarioDao ud = new UsuarioDao();
        LoginController lc = new LoginController(tl, ud);
}
}