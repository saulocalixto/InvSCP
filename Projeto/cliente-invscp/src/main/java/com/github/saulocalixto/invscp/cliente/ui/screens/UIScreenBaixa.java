/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.BaixaAPI;
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
public class UIScreenBaixa extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            cadastrar();
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarTodos();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            try {
                editar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            try {
                deletar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBaixa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
    
    private static void cadastrar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void visualizar() throws IOException {
        final String id = IO.readString("Insira o id do Departamento:");
        final String json = BaixaAPI.getBaixa(id);

        if (BaixaAPI.isJsonValid(json)) {
            mostrar(new JSONObject(json).getJSONObject("data").toString());
        }
    }
    
    private static void visualizarTodos() throws IOException {
        JSONArray array = new JSONObject(BaixaAPI.getBaixas()).getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            mostrar(array.get(i).toString());
        }
    }
    
    private static void editar() throws IOException {
        final String id = IO.readString("Insira o id da Baixa:");
        final String idBem = IO.readString("Insira o id do Bem Patrimonial:");
        final String data = IO.readString("Insira a data da Baixa:");
        final String observacao = IO.readString("Insira a observação da Baixa:");
        
        BaixaAPI.editarBaixa(id, idBem, data, observacao);
    }
    
    private static void deletar() throws IOException {
        final String id = IO.readString("Insira o id da Baixa:");
        BaixaAPI.deletarBaixa(id);
    }
    
    private static void mostrar(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nid: " + obj.get("id"));
        System.out.println("id do Bem Patrimonial: " + obj.get("idBem"));
        System.out.println("Data: " + obj.get("data"));
        System.out.println("Observacao: " + obj.get("observacao"));
    }
    
    public UIScreenBaixa() {
        super("Baixa", opcoes);
    }
    
}
