/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.TerminalUI;
import java.io.IOException;

/**
 * Classe principal do Cliente InvSCP
 * @author Lucas Sampaio Dias
 */
public class Aplicacao {
    private static final int MAX_OPCOES_MENU_PRINCIPAL = 2;
    
    private static boolean sair = false;
    private static Usuario usuario;
    
    public static void main(String[] args) throws IOException {
        int opcao = 0;
        usuario = new Usuario();
        
        TerminalUI.escreverMensagemInicial();

        if (usuario.login() == false) {
            return;
        }
        System.out.println("Bem-vindo, " + usuario.getNome() + "!");
        
        //O primeiro menu a ser mostrado é o menu inicial (0)
        TerminalUI.acessarMenu(0);
        
        while (sair == false) {
            TerminalUI.escreverOpcoes();
            opcao = IO.readInt();
            tratarOpcao(opcao);
        }
        
        System.out.println("\nObrigado por usar o InvSCP!\n");
    }
    
    /**
     * Realiza a devida operação de acordo com a opção inserida e com o menu
     * atual. Esta opção pode ser uma simples mudança de menu, requisitar um
     * serviço ou mesmo sair da aplicação.
     * @param opcao 
     */
    private static void tratarOpcao(int opcao) {
        switch (TerminalUI.menuAtual()) {
            case "Menu Principal":
                switch (opcao) {
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                    case 0:
                        sair = true;
                        break;
                    case 1:
                    case 2:
                        TerminalUI.acessarMenu(opcao);
                        break;
                }
                break;
            case "Bens":
                switch (opcao) {
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                    case 0:
                        TerminalUI.voltarMenu();
                        break;
                    case 1:
                        //Listar bens...
                        break;
                }
                break;
            case "Locais":
                switch (opcao) {
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        break;
                    case 0:
                        TerminalUI.voltarMenu();
                        break;
                }
                break;
        }
    }
}
