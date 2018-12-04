/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.SalaAPI;
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
public class UIScreenSalas extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            try {
                criarSala();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                editarSala();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            try {
                deletarSala();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenSalas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
    
    public UIScreenSalas() {
        super("Salas", opcoes);
    }
    
    private static void deletarSala() throws IOException {
        final String id = IO.readString("Insira o id da Sala:");
        SalaAPI.deletaSala(id);
    }
    
    private static void editarSala() throws IOException {
        final String id = IO.readString("Insira o id da Sala:");
        final String numSala = IO.readString("Insira o número da Sala:");
        final String idDepartamento = IO.readString("Insira o id do Departamento:");
        final String idPredio = IO.readString("Insira o id do Prédio:");
        
        SalaAPI.editarSala(id, numSala, idDepartamento, idPredio);
    }
    
    private static void criarSala() throws IOException {
        final String numSala = IO.readString("Insira o número da Sala:");
        final String idDepartamento = IO.readString("Insira o id do Departamento:");
        final String idPredio = IO.readString("Insira o id do Prédio:");
        
        SalaAPI.criaSala(numSala, idDepartamento, idPredio);
    }
    
    private static void visualizarSala() throws IOException {
        final String id = IO.readString("Insira o id da Sala:");
        final String json = SalaAPI.getSala(id);
        if (SalaAPI.isJsonValid(json)) {
            mostrarSala(new JSONObject(json).getJSONObject("data").toString());
        }
    }
    
    private static void visualizarSalas() throws IOException {
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
        System.out.println("id: " + obj.get("id"));
    }
}
