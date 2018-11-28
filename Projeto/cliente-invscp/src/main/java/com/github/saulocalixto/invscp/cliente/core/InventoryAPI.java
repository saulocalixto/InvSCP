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
    private static final String BASE_URL = "http://localhost:8090";
    
    public static String login(String email, String senha) 
            throws MalformedURLException, IOException {
        String json = getJson(new URL(BASE_URL + "/login?email=" + email +
                "&senha=" + senha));
        return json;
    }
    
    public static String getUsuario(String email) 
            throws MalformedURLException, IOException {
        return getJson(new URL(BASE_URL + "/usuario/consulte?email=" + email));
    }
    
    public static void deletarUsuario(String email) {
        System.out.println("Funcionalidade ainda não implementada!");
    }
    
    private static String getJson(URL url) throws IOException {

        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                file.append(sc.nextLine());
            }
        }
        return file.toString();
    }
}
