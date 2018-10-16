/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import com.github.saulocalixto.invscp.cliente.ui.IO;
import java.io.Console;
import java.io.IOException;

/**
 * Classe responsável por gerenciar o usuário atual da sessão
 * @author Lucas Sampaio Dias
 */
public class Usuario {
    private String nome;
    
    /**
     * Realiza o login do usuário, pedindo na saída principal o nome do usuário
     * e sua senha.
     * @return true se o login foi realizado com sucesso, false se a tentativa 
     * de login falhar.
     */
    public boolean login() throws IOException {
        Console console = System.console();
        boolean logado = false;
        
        while (logado == false) {
            nome = IO.readNomeUsuario("Usuário: ");
            String senha = IO.readSenha("Senha: ");
            logado = checarSenha(senha);
        }
        
        return true;
    }
    
    private static boolean checarSenha(String senha) {
        //Falta implementar
        return true;
    }
    
    public String getNome() {
        return nome;
    }
}
