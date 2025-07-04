/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.siga.view.cadastros.Dialog;

import java.awt.event.ActionListener;

/**
 *
 * @author Julio
 */
public interface DialogEntidade {
    
    public void addEntidadeListener(ActionListener listener);
    public void showMessage(String message);
    public void limparInputs();
    public void setVisible(boolean b);
}
