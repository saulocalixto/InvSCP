/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui;

/**
 * Define uma opção que o usuário poderá selecionar na interface, tendo um
 * nome e sua ação (abrir outra tela, requisitar um serviço, etc.)
 * @author Lucas Sampaio Dias
 */
public class UIScreenOption {
    public final String nome;
    public final Runnable acao;
    
    public UIScreenOption(String nome, Runnable acao) {
        this.nome = nome;
        this.acao = acao;
    }
}
