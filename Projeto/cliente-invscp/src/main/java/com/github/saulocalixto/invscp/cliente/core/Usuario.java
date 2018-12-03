/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import com.github.saulocalixto.invscp.cliente.api.InventoryAPI;
import com.github.saulocalixto.invscp.cliente.api.LoginAPI;
import com.github.saulocalixto.invscp.cliente.ui.IO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Classe responsável por gerenciar o usuário atual da sessão
 * @author Lucas Sampaio Dias
 */
public class Usuario {
    private String email;
    private Gson gson = new Gson();
    
    /**
     * Realiza o login do usuário, pedindo na saída principal o nome do usuário
     * e sua senha.
     * @return true se o login foi realizado com sucesso, false se a tentativa 
     * de login falhar.
     */
    public boolean loginTerminal() throws IOException {
        String logado = null;

        while (logado == null) {
            email = IO.readNomeUsuario("E-mail: ");
            String senha = IO.readSenha("Senha: ");
            logado = checarLogin(email, senha);
        }

        return getAutorizacao(logado);
    }

    private boolean getAutorizacao(String json) {
        JsonElement jelem = gson.fromJson(json, JsonElement.class);
        JsonObject jobj = jelem.getAsJsonObject();
        InventoryAPI.setAuth(jobj.get("tokenAcesso").getAsString());
        return true;
    }

    public static boolean isEmail(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        return true;
    }
    
    private String checarLogin(String email, String senha) {
        try {
            return LoginAPI.login(email, senha);
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getNome() {
        return email;
    }
}
