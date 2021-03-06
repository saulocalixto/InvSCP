/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.BemPatrimonialAPI;
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
public class UIScreenBemPatrimonial extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            try {
                cadastrar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBemPatrimonial.class.getName()).log(Level.SEVERE, null, ex);

            }
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBemPatrimonial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarTodos();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBemPatrimonial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            try {
                editar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBemPatrimonial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            try {
                deletar();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenBemPatrimonial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
    
    private static void cadastrar() throws IOException {
        final String localAtual = IO.readString("Insira o localAtual do Bem Patrimonial:");
        final String numeroDeTombamento = IO.readString("Insira o numeroDeTombamento do Bem Patrimonial:");
        final String denominacao = IO.readString("Insira o denominacao do Bem Patrimonial:");
        final String dataDeAquisicao = IO.readString("Insira o dataDeAquisicao do Bem Patrimonial:");
        final String especificacao = IO.readString("Insira o especificacao do Bem Patrimonial:");
        final String garantia = IO.readString("Insira o garantia do Bem Patrimonial:");
        final String marca = IO.readString("Insira o marca do Bem Patrimonial:");
        final String valorDeCompra = IO.readString("Insira o valorDeCompra do Bem Patrimonial:");
        final String situacao = IO.readString("Insira o situacao do Bem Patrimonial:");
        final String notaFiscal = IO.readString("Insira o notaFiscal do Bem Patrimonial:");
        final String grupoDeMaterial = IO.readString("Insira o grupoDeMaterial do Bem Patrimonial:");
        BemPatrimonialAPI.criaBemParimonial(localAtual, numeroDeTombamento, denominacao, dataDeAquisicao,
                especificacao, garantia, marca, valorDeCompra, situacao, notaFiscal, grupoDeMaterial);
    }
    
    private static void visualizar() throws IOException {
        final String id = IO.readString("Insira o id do Bem Patrimonial:");
        final String json = BemPatrimonialAPI.getBemParimonial(id);

        if (BemPatrimonialAPI.isJsonValid(json)) {
            mostrar(new JSONObject(json).getJSONObject("data").toString());
        }
    }
    
    private static void visualizarTodos() throws IOException {
        JSONArray array = new JSONObject(BemPatrimonialAPI.getBemParimoniais()).getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            mostrar(array.get(i).toString());
        }
    }
    
    private static void editar() throws IOException {
        final String id = IO.readString("Insira o id do Bem Patrimonial:");
        final String localAtual = IO.readString("Insira o localAtual do Bem Patrimonial:");
        final String numeroDeTombamento = IO.readString("Insira o numeroDeTombamento do Bem Patrimonial:");
        final String denominacao = IO.readString("Insira o denominacao do Bem Patrimonial:");
        final String dataDeAquisicao = IO.readString("Insira o dataDeAquisicao do Bem Patrimonial:");
        final String especificacao = IO.readString("Insira o especificacao do Bem Patrimonial:");
        final String garantia = IO.readString("Insira o garantia do Bem Patrimonial:");
        final String marca = IO.readString("Insira o marca do Bem Patrimonial:");
        final String valorDeCompra = IO.readString("Insira o valorDeCompra do Bem Patrimonial:");
        final String situacao = IO.readString("Insira o situacao do Bem Patrimonial:");
        final String notaFiscal = IO.readString("Insira o notaFiscal do Bem Patrimonial:");
        final String grupoDeMaterial = IO.readString("Insira o grupoDeMaterial do Bem Patrimonial:");
        BemPatrimonialAPI.editaBemParimonial(id, localAtual, numeroDeTombamento, denominacao, dataDeAquisicao,
                especificacao, garantia, marca, valorDeCompra, situacao, notaFiscal, grupoDeMaterial);
    }
    
    private static void deletar() throws IOException {
        final String id = IO.readString("Insira o id do Bem Patrimonial:");
        BemPatrimonialAPI.deletarBemParimonial(id);
    }
    
    private static void mostrar(String json) {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nid: " + obj.get("id"));
        System.out.println("Número de Tombamento: " + 
                obj.get(BemPatrimonialAPI.NUMERO_TOMBAMENTO));
        System.out.println("Local Atual: " + 
                obj.get(BemPatrimonialAPI.LOCAL_ATUAL));
        System.out.println("Denominação: " + 
                obj.get(BemPatrimonialAPI.DENOMINACAO));
        System.out.println("Data de Aquisição: " + 
                obj.get(BemPatrimonialAPI.DATA_AQUISICAO));
        System.out.println("Especificação: " + 
                obj.get(BemPatrimonialAPI.ESPECIFICACAO));
        System.out.println("Garantia: " + 
                obj.get(BemPatrimonialAPI.GARANTIA));
        System.out.println("Marca: " + 
                obj.get(BemPatrimonialAPI.MARCA));
        System.out.println("Valor de Compra: " + 
                obj.get(BemPatrimonialAPI.VALOR_COMPRA));
        System.out.println("Nota Fiscal: " + 
                obj.get(BemPatrimonialAPI.NOTA_FISCAL));
        System.out.println("Ordem de Serviço: " + 
                obj.get(BemPatrimonialAPI.ORDEM_DE_SERVICO));
    }
    
    public UIScreenBemPatrimonial() {
        super("Bem Patrimonial", opcoes);
    }
    
}
