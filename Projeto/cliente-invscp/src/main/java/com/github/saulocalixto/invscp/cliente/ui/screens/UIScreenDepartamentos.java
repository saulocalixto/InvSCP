/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.DepartamentoAPI;
import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
            try {
                cadastrar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarTodos();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            try {
                editar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            try {
                deletar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenDepartamentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
    
    private static void cadastrar() throws IOException {
        final String nome = IO.readString("Insira o nome do Departamento:");
        DepartamentoAPI.criaDepartamento(nome);
    }
    
    private static void visualizar() throws IOException {
        final String id = IO.readString("Insira o id do Departamento:");
        final String json = DepartamentoAPI.getDepartamento(id);

        if (DepartamentoAPI.isJsonValid(json)) {
            mostrar(new JSONObject(json).getJSONObject("data").toString());
        }
    }
    
    private static void visualizarTodos() throws IOException {
        JSONArray array = new JSONObject(DepartamentoAPI.getDepartamentos()).getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            mostrar(array.get(i).toString());
        }
    }
    
    private static void editar() throws IOException {
        final String nome = IO.readString("Insira o nome do Departamento:");
        final String id = IO.readString("Insira o id do Departamento:");
        DepartamentoAPI.editarDepartamento(id, nome);
    }
    
    private static void deletar() throws IOException {
        final String id = IO.readString("Insira o id do Departamento:");
        DepartamentoAPI.deletaDepartamento(id);
    }
    
    private static void mostrar(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nid: " + obj.get("id"));
        System.out.println("Nome: " + obj.get("nomeDoDepartamento"));
    }
    
    public UIScreenDepartamentos() {
        super("Departamentos", opcoes);
    }
    
}
