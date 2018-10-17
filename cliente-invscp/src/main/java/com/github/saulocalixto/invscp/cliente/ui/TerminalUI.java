/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui;

import com.github.saulocalixto.invscp.cliente.ui.screens.UIScreenMenuPrincipal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Realiza a comunicação entre o usuário e a aplicação através do Terminal.
 * @author Lucas Sampaio Dias
 */
public class TerminalUI {
    private static ArrayList<Integer> menu = new ArrayList<Integer>();
    private static Stack<UIScreen> menusAbertos = new Stack<>();
    
    /**
     * Escreve na saída principal a mensagem de boas-vindas.
     */
    public static void escreverMensagemInicial() {
        System.out.println("############################");
        System.out.println("###### Cliente InvSCP ######");
        System.out.println("############################");
    }
    
    public static void mostrar(UIScreen menu) throws IOException {
        menusAbertos.add(menu);
        mostrarMenuAtual();
    }
    
    public static void mostrarMenuAtual() throws IOException {
        menusAbertos.peek().mostrar();
        menusAbertos.peek().lerOpcao();
    }
    
    public static void voltar() throws IOException {
        if (menusAbertos.size() > 1) {
            menusAbertos.pop();
            mostrarMenuAtual();
        }
        else {
            System.out.println("\nObrigado por usar o InvSCP!\n");
            System.exit(0);
        }
    }
    
    public static String opcaoZero() {
        if (menusAbertos.size() <= 1) {
            return "0 - Sair\n";
        }
        else {
            return "0 - Voltar\n";
        }
    }
    
    public static void reset() {
        menusAbertos = new Stack<>();
        menusAbertos.add(new UIScreenMenuPrincipal());
    }
}
