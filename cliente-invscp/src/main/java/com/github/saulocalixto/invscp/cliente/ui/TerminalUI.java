/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui;

import java.util.ArrayList;

/**
 * Realiza a comunicação entre o usuário e a aplicação através do Terminal.
 * @author Lucas Sampaio Dias
 */
public class TerminalUI {
    private static ArrayList<Integer> menu = new ArrayList<Integer>();
    
    /**
     * Acessa o menu requisitado tendo como referência o menu atual.
     * Exemplo: Se o menu atual for "Menu Principal", acessarMenu(1) acessa o
     * menu "Bens".
     * @param opcao id do menu a ser acessado de acordo com o menu atual.
     */
    public static void acessarMenu(int opcao) {
        TerminalUI.menu.add(opcao);
    }
    
    /**
     * Retorna o nome do menu atual.
     * @return Nome do menu atual (ex.: "Menu Principal")
     */
    public static String menuAtual() {
        int pilha = 1;
        int menuID = 0;
        for (int i = 0; i < menu.size(); i++) {
            menuID += menu.get(i) * pilha;
            pilha *= 10;
        }
        
        switch (menuID) {
            case 0:
                return "Menu Principal";
            case 10:
                return "Bens";
            case 20:
                return "Locais";
        }
        return "";
    }
    
    /**
     * Sai do menu atual, voltando para o menu anterior.
     */
    public static void voltarMenu() {
        menu.remove(menu.size() - 1);
    }
    
    /**
     * Escreve na saída principal a mensagem de boas-vindas.
     */
    public static void escreverMensagemInicial() {
        System.out.println("############################");
        System.out.println("###### Cliente InvSCP ######");
        System.out.println("############################");
    }

    /**
     * Escreve na saída principal as opções do menu atual
     */
    public static void escreverOpcoes() {
        switch (menuAtual()) {
            case "Menu Principal":
                System.out.println("\nInsira o número correspondente à opção "
                        + "desejada");
                System.out.println("1 - Bens");
                System.out.println("2 - Locais");
                System.out.println("0 - Sair");
                System.out.println("");
                break;
            case "Bens":
                System.out.println("\nBENS");
                System.out.println("Insira o número correspondente à opção "
                        + "desejada");
                System.out.println("0 - Voltar");
                System.out.println("");
                break;
            case "Locais":
                System.out.println("\nLOCAIS");
                System.out.println("Insira o número correspondente à opção "
                        + "desejada");
                System.out.println("1 - Departamentos");
                System.out.println("2 - Prédios");
                System.out.println("0 - Voltar");
                System.out.println("");
                break;
        }
    }
}
