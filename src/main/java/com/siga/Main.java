/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siga;

import com.siga.dao.UsuarioDao;
import com.siga.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]) throws SQLException{
    ArrayList<Usuario> lista = UsuarioDao.listar();
    for(Usuario u : lista){
        System.out.println(u.getNome());
    }
   
   
   
    }
}
