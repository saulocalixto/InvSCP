/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

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
    public boolean Login() {
        //Falta implementar lógica de login, tratando nome e senha
        
        nome = "Admin";
        return true;
    }
    
    public String getNome() {
        return nome;
    }
}
