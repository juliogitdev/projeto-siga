package com.siga.controller;

import com.siga.dao.UsuarioDao;
import com.siga.model.Usuario;
import com.siga.view.PerfilView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Julio
 */
public class PerfilController {

    private PerfilView dialogPerfil;
    private UsuarioDao usuarioDao;
    private Usuario usuario;

    public PerfilController(JFrame parent, Usuario usuario) {
        this.dialogPerfil = new PerfilView(parent);
        this.usuarioDao = new UsuarioDao();
        this.usuario = usuario;
    }

    public void iniciar() {
        dialogPerfil.salvarButtonListener(new SalvarButtonListener());
        carregarCampos();
        dialogPerfil.setVisible(true);
    }

    public void close() {
        dialogPerfil.dispose();
    }

    public void carregarCampos() {
        dialogPerfil.setNomeCompleto(usuario.getNome());
        dialogPerfil.setEmail(usuario.getEmail());
    }

    public Usuario montarUpdateUser() {
        Usuario newUser = usuario;
        newUser.setNome(dialogPerfil.getNomeCompleto());
        newUser.setEmail(dialogPerfil.getEmail());

        return newUser;
    }

    public boolean verificarEmail() {
        String emailNovo = dialogPerfil.getEmail().trim();

        if (emailNovo.equals(usuario.getEmail())) {
            return true;
        }

        if (!emailNovo.contains("@") & !emailNovo.contains(".")) {
            return false;
        }

        Usuario userExistente = usuarioDao.buscarPorEmail(emailNovo);

        if (userExistente == null) {
            return true;
        }

        return false;

    }

    public boolean verificarNome() {
        String nomeNovo = dialogPerfil.getNomeCompleto();

        if (nomeNovo.isBlank()) {
            return false;
        }

        if (nomeNovo.length() < 5) {
            return false;
        }

        return true;
    }

    public boolean salvarUser(Usuario newUser) {
        try {
            usuarioDao.atualizar(newUser);
            usuario = newUser;
            close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    class SalvarButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!verificarEmail()) {
                dialogPerfil.showMessage("Digite um e-mail válido");
                return;
            }

            if (!verificarNome()) {
                dialogPerfil.showMessage("Digite um nome válido!");
                return;
            }

            Usuario newUser = montarUpdateUser();
            salvarUser(newUser);

        }

    }
}
