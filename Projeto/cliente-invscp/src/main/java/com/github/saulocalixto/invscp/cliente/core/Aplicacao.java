/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.TerminalUI;
import com.github.saulocalixto.invscp.cliente.ui.screens.UIScreenMenuPrincipal;
import java.io.IOException;

/**
 * Classe principal do Cliente InvSCP
 * @author Lucas Sampaio Dias
 */
public class Aplicacao {
    private static Usuario usuario;
    
    public static void main(String[] args) throws IOException {
        int opcao = 0;
        usuario = new Usuario();
        
        TerminalUI.escreverMensagemInicial();

        if (usuario.login() == false) {
            return;
        }
        System.out.println("Bem-vindo, " + usuario.getNome() + "!\n");
        
        TerminalUI.reset();
        while (true) {
            TerminalUI.mostrarMenuAtual();
        }
    }
}
