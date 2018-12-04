/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenBemPatrimonial extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            cadastrar();
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            visualizar();
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            visualizarTodos();
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            editar();
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            deletar();
        }));
    }
    
    private static void cadastrar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void visualizar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void visualizarTodos() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void editar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void deletar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void mostrar(String json) {
        JSONObject obj = new JSONObject(json);
        //System.out.println("\nid: " + obj.get("id"));
        //System.out.println("Nome: " + obj.get("nome"));
    }
    
    public UIScreenBemPatrimonial() {
        super("Bem Patrimonial", opcoes);
    }
    
}
