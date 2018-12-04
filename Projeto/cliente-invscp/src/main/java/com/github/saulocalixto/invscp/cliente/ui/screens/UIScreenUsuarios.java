/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.ui.screens;

import com.github.saulocalixto.invscp.cliente.api.UsuarioAPI;
import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.github.saulocalixto.invscp.cliente.ui.UIScreen;
import com.github.saulocalixto.invscp.cliente.ui.UIScreenOption;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Lucas Sampaio Dias
 */
public class UIScreenUsuarios extends UIScreen{
    
    private static final HashMap<Integer, UIScreenOption> opcoes;
    static
    {
        opcoes = new HashMap<>();
        opcoes.put(1, new UIScreenOption("Cadastrar", () -> {
            try {
                cadastrarUsuario();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(2, new UIScreenOption("Visualizar", () -> {
            try {
                visualizarUsuario();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(3, new UIScreenOption("Visualizar Todos", () -> {
            try {
                visualizarUsuarios();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(4, new UIScreenOption("Editar", () -> {
            try {
                editarUsuario();
            } catch (IOException ex) {
                Logger.getLogger(UIScreenUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        opcoes.put(5, new UIScreenOption("Deletar", () -> {
            deletarUsuario();
        }));
    }
    
    private static void cadastrarUsuario() throws IOException {
        final String email = IO.readString("Insira o email do Usuario:");
        final String senha = IO.readString("Insira a senha do Usuario:");
        final String nome = IO.readString("Insira o nome do Usuario:");
        final String cpf = IO.readString("Insira o cpf do Usuario:");
        final String idDepartamento = IO.readString("Insira o id do Departamento:");
        final String grupo = IO.readString("Insira o grupo:");
        
        UsuarioAPI.criaUsuario(email, senha, nome, cpf, idDepartamento, grupo);
    }

    private static void visualizarUsuario() throws IOException {
        final String email = IO.readString("Insira o e-mail do usuario:");
        final String json = UsuarioAPI.getUsuario(email);

        if (UsuarioAPI.isJsonValid(json)) {
            mostrarUsuario(json);
        }
    }
    
    private static void visualizarUsuarios() throws IOException {
        JSONArray array = new JSONObject(UsuarioAPI.getUsuarios()).getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            mostrarUsuario(array.getJSONObject(i).toString());
        }
    }
    
    private static void editarUsuario() throws IOException {
        final String email = IO.readString("Insira o email do Usuario:");
        final String senha = IO.readString("Insira a senha do Usuario:");
        final String nome = IO.readString("Insira o nome do Usuario:");
        final String cpf = IO.readString("Insira o cpf do Usuario:");
        final String idDepartamento = IO.readString("Insira o id do Departamento:");
        final String grupo = IO.readString("Insira o grupo:");
        
        UsuarioAPI.editarUsuario(email, senha, nome, cpf, idDepartamento, grupo);
    }
    
    private static void deletarUsuario() {
        final String email;
        try {
            email = IO.readString("Insira o e-mail do usuario:");
            UsuarioAPI.deletarUsuario(email);
        } catch (Exception e) {
            System.out.println("Não foi possível deletar o usuário.");
        }
    }
    
    private static void mostrarUsuario(final String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        System.out.println("\nNome: " + obj.get("nome"));
        System.out.println("E-mail: " + obj.get("email"));
        System.out.println("Grupo: " + obj.get("grupo"));
        System.out.println("Departamento: " + obj.get("departamento"));
    }
    
    public UIScreenUsuarios() {
        super("Usuários", opcoes);
    }
    
}
