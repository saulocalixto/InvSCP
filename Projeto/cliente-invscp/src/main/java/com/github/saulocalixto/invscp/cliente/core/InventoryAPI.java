/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Implementa as chamadas de API com o Servidor InvSCP.
 * @author Lucas Sampaio Dias
 */
public class InventoryAPI {
    private static final String BASE_URL = "http://localhost:8080";
    
    public static String login(String email, String senha) 
            throws MalformedURLException, IOException {
        return getJson(formadorDeUrl(BASE_URL + "/login?email=" + email +
                "&senha=" + senha), "GET", null);
    }
    
    public static String getUsuario(String email) 
            throws MalformedURLException, IOException {
        return getJson(formadorDeUrl(BASE_URL + "/usuario?email=" + email),
                "GET", "4b9e32d8-2a7b-4361-bd50-57b13714f8c7");
    }
    
    public static String deletarUsuario(String email) {
        //System.out.println("Funcionalidade ainda não implementada!");
        try {
            return getJson(formadorDeUrl(BASE_URL + "/usuario?email=" + email),
                    "DELETE", "4b9e32d8-2a7b-4361-bd50-57b13714f8c7");
        } catch (IOException e) {
            return "{}";
        }
    }

    private static URL formadorDeUrl(String... parametros) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();
        for (String s:
             parametros) {
            builder.append(s);
        }
        return new URL(builder.toString());
    }

    private static String getJson(URL url, String metodoRequisicao, String auth) throws IOException {

        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //conn.setRequestMethod("GET");
        conn.setRequestMethod(metodoRequisicao);
        conn.setRequestProperty("Autorizacao", auth);
        conn.connect();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200 && responsecode != 202) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }
        else {
            Scanner sc = new Scanner(conn.getInputStream());
            while (sc.hasNext()) {
                file.append(sc.nextLine());
            }
        }
        return file.toString();
    }
}
