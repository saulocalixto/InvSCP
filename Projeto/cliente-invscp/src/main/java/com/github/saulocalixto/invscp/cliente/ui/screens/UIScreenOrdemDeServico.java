/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.OrdemDeServicoAPI;
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
public class UIScreenOrdemDeServico extends UIScreen{
    
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
                Logger.getLogger(UIScreenOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarTodos();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenOrdemDeServico.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    private static void visualizar() throws IOException {
        final String id = IO.readString("Insira o id da OS:");
        final String json = OrdemDeServicoAPI.getOrdemDeServico(id);

        if (OrdemDeServicoAPI.isJsonValid(json)) {
            mostrar(new JSONObject(json).getJSONObject("data").toString());
        }
    }
    
    private static void visualizarTodos() throws IOException {
        JSONArray array = new JSONObject(OrdemDeServicoAPI.getOrdensDeServico()).getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            mostrar(array.get(i).toString());
        }
    }
    
    private static void editar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void deletar() {
        System.out.println("Funcionalidade não implementada");
    }
    
    private static void mostrar(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nid: " + obj.get("id"));
        System.out.println("Número de Tombamento: " + 
                obj.get(OrdemDeServicoAPI.ID));
        System.out.println("Bem Patrimonial: " + 
                obj.get(OrdemDeServicoAPI.BEM_PATRIMONIAL));
        System.out.println("Identificador da Ordem: " + 
                obj.get(OrdemDeServicoAPI.IDENTIFICADOR_ORDEM));
        System.out.println("Motivo: " + 
                obj.get(OrdemDeServicoAPI.MOTIVO));
        System.out.println("Observação: " + 
                obj.get(OrdemDeServicoAPI.OBSERVACAO));
        System.out.println("Data de Abertura: " + 
                obj.get(OrdemDeServicoAPI.DATA_ABERTURA));
        System.out.println("Data de Encerramento: " + 
                obj.get(OrdemDeServicoAPI.DATA_ENCERRAMENTO));
        System.out.println("Nome da Prestadora: " + 
                obj.get(OrdemDeServicoAPI.NOME_PRESTADORA));
    }
    
    public UIScreenOrdemDeServico() {
        super("Ordem de Serviço", opcoes);
    }
    
}
