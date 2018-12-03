/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Implementa as chamadas de API com o Servidor InvSCP.
 * @author Lucas Sampaio Dias
 */
public abstract class InventoryAPI {
    private static final String BASE_URL = "http://localhost:8080";

    private static final String CONSULTA = "GET";

    private static final String ATUALIZACAO = "PUT";

    private static final String EXCLUSAO = "DELETE";

    private static final String ENDPOINT_PADRAO = null;

    private static String auth = "";

    static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";

    static final String PARAMETRO_DE_CONSULTA = "id";

    static final String PARAMETRO_DE_DELECAO = "id";

    private static final String JSON_ERRO = "{\"erro\":[{\"mensagem\":\"Ocorreu um erro no banco de dados." +
            " Por favor revise o que foi enviado ou reinicie o servidor.\",\"conceito\":\"Banco de dados\"}]}";

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        InventoryAPI.auth = auth;
    }

    static String chamadaGet(String... urlParameters) {
        try {
            return validaInconsistencia(chamadaApi(formadorDeUrl(urlParameters), CONSULTA, null));
        } catch (Exception e) {
            return validaInconsistencia(JSON_ERRO);
        }
    }

    static String chamadaPut(String... urlParameters) {
        try {
            String endpoint = urlParameters[0];
            String[] body = new String[urlParameters.length-1];
            System.arraycopy(urlParameters, 1, body, 0, urlParameters.length - 1);
            return validaInconsistencia(chamadaApi(formadorDeUrl(endpoint), ATUALIZACAO, body));
        } catch (Exception e) {
            return validaInconsistencia(JSON_ERRO);
        }
    }

    static String chamadaDelete(String... urlParameters) {
        try {
            return validaInconsistencia(chamadaApi(formadorDeUrl(urlParameters), EXCLUSAO, null));
        } catch (Exception e) {
            return validaInconsistencia(JSON_ERRO);
        }
    }

    private static URL formadorDeUrl(String... parametros) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();
        int numeroDeParametros = 0;
        for (String s:
                parametros) {
            builder.append(concatenaParametrosAdicionais(numeroDeParametros));
            builder.append(s);
            ++numeroDeParametros;
        }
        return new URL(BASE_URL + builder.toString());
    }

    private static String concatenaParametrosAdicionais(int numeroDeParametros) {
        return (numeroDeParametros == 0) ? ""
                : (numeroDeParametros == 1) ? "?"
                : (numeroDeParametros % 2 == 0) ? "="
                : "&";
    }

    private static String chamadaApi(URL url, String metodoRequisicao, String... body) throws IOException {
        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(metodoRequisicao);
        conn.setRequestProperty("Autorizacao", auth);
        conn.setRequestProperty("content-type", "application/json");
        prepareBody(conn, body);
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode < 200 || responseCode > 299) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
        else {
            Scanner sc = new Scanner(conn.getInputStream());
            while (sc.hasNext()) {
                file.append(sc.nextLine());
            }
        }
        return file.toString();
    }

    private static void prepareBody(HttpURLConnection connection, String... body) {
        if (body == null) {
            return;
        }
        try {
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            JSONObject json = new JSONObject();
            for (int i = 0; i < body.length; i += 2){
                json.put(body[i], body[i+1]);
            }
            byte[] outputInBytes = json.toString().getBytes("UTF-8");
            os.write( outputInBytes );
            os.close();
        } catch (Exception ignored) {}
    }

    private static String validaInconsistencia(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();

            JsonElement erro = jsonObject.get("erro");

            if(erro.isJsonObject()){
                JsonObject mensagem = erro.getAsJsonObject();

                JsonElement valor = mensagem.get("mensagem");

                if(valor.isJsonObject()){
                    System.out.println(valor.getAsString());
                }
            }
        }
        return json;
    }

    public static boolean isJsonValid(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement erro = jsonObject.get("erro");
            if(erro.isJsonObject()){
                return false;
            }
        }
        return true;
    }
}
