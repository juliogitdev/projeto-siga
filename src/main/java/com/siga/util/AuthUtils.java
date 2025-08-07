package com.siga.util;

/**
 *
 * @author Julio
 */

import org.mindrot.jbcrypt.BCrypt;

public class AuthUtils {
    
    public static String hashSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    
    public static boolean verificarSenha(String senhaDigitada, String senhaUser){
        return BCrypt.checkpw(senhaDigitada, senhaUser);
    }
    
}
