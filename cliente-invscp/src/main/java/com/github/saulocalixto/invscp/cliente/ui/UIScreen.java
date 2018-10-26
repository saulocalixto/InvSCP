/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que auxilia na criação e manutenção das interfaces via Terminal
 * @author Lucas Sampaio Dias
 */
public class UIScreen {
    public final String titulo;
    public final HashMap<Integer, UIScreenOption> opcoes;
    
    public UIScreen(String titulo, HashMap<Integer, UIScreenOption> opcoes) {
        this.titulo = titulo;
        this.opcoes = opcoes;
    }
    
    public void mostrar() {
        System.out.println("\n" + titulo);
        for(Map.Entry<Integer, UIScreenOption> elemento : opcoes.entrySet()) {
            System.out.println(elemento.getKey() + " - " + 
                    elemento.getValue().nome);
        }
        System.out.println(TerminalUI.opcaoZero());
    }
    
    private void executarOpcao(int opcao) throws IOException {
        if (opcao == 0) {
            TerminalUI.voltar();
        }
        else if (opcoes.containsKey(opcao)) {
            opcoes.get(opcao).acao.run();
        }
        else {
            System.out.println("Opção inválida!");
        }
    }
    
    public void lerOpcao() throws IOException {
        boolean opcaoValida = false;
        int opcao = -1;
        while (opcaoValida == false) {
            opcao = IO.readInt();
            if (opcao != 0 && opcoes.containsKey(opcao) == false) {
                System.out.println("Opção inválida, tente novamente.");
            }
            else {
                opcaoValida = true;
            }
        }
        executarOpcao(opcao);
    }
}
