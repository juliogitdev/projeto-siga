package com.siga;


import com.siga.controller.Entidades.CategoriaController;
import com.siga.controller.auth.LoginController;
import com.siga.controller.PrincipalController;
import com.siga.dao.ProdutoDao;
import com.siga.dao.UsuarioDao;
import com.siga.model.Usuario;
import com.siga.view.TelaPrincipal;
import com.siga.view.auth.TelaLogin;
import com.siga.view.entidade.CategoriaView;
import java.sql.SQLException;

public class Main {
    public static void main (String args[]) throws SQLException{
        new LoginController();

    }
}