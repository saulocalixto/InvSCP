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
public class UIScreenDepartamentos extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            cadastrarDepartamento();
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            visualizarDepartamento();
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            visualizarDepartamentos();
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            editarDepartamento();
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            deletarDepartamento();
        }));
    }
    
    private static void cadastrarDepartamento() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void visualizarDepartamento() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void visualizarDepartamentos() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void editarDepartamento() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void deletarDepartamento() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void mostrarDepartamento(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nid: " + obj.get("id"));
        System.out.println("Nome: " + obj.get("nome"));
    }
    
    public UIScreenDepartamentos() {
        super("Departamentos", opcoes);
    }
    
}
