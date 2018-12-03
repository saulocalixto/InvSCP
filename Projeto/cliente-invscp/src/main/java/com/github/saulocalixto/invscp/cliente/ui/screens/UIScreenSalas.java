/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.SalaAPI;
import com.github.saulocalixto.invscp.cliente.api.UsuarioAPI;
import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.TerminalUI;
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
public class UIScreenSalas extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            System.out.println("Funcionalidade não implementada");
            SalaAPI.criaSala("666", "departamento001", "predio001");
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizarSala();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarSalas();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            System.out.println("Funcionalidade não implementada");
        }));
    }
    
    public UIScreenSalas() {
        super("Salas", opcoes);
    }
    
    public static void visualizarSala() throws IOException {
        final String id = IO.readString("Insira o id da Sala:");
        final String json = SalaAPI.getSala(id);

        mostrarSala(new JSONObject(json).getJSONObject("data").toString());
    }
    
    public static void visualizarSalas() throws IOException {
        JSONArray salas = new JSONObject(SalaAPI.getSalas()).getJSONArray("data");

        for (int i = 0; i < salas.length(); i++) {
            mostrarSala(salas.get(i).toString());
        }
    }
    
    private static void mostrarSala(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nNúmero: " + obj.get("numeroSala"));
        System.out.println("Prédio: " + obj.get("idPredio"));
        System.out.println("Departamento: " + obj.get("idDepartamento"));
    }
}
