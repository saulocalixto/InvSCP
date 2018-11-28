/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import com.github.saulocalixto.invscp.cliente.ui.IO;
import java.io.IOException;

/**
 * Classe responsável por gerenciar o usuário atual da sessão
 * @author Lucas Sampaio Dias
 */
public class Usuario {
    private String email;
    
    /**
     * Realiza o login do usuário, pedindo na saída principal o nome do usuário
     * e sua senha.
     * @return true se o login foi realizado com sucesso, false se a tentativa 
     * de login falhar.
     */
    public boolean loginTerminal() throws IOException {
        boolean logado = false;
        
        while (logado == false) {
            email = IO.readNomeUsuario("E-mail: ");
            String senha = IO.readSenha("Senha: ");
            logado = checarLogin(email, senha);
        }
        
        return true;
    }
    
    public static boolean isEmail(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        return true;
    }
    
    private boolean checarLogin(String email, String senha) {
        try {
            InventoryAPI.login(email, senha);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public String getNome() {
        return email;
    }
}
