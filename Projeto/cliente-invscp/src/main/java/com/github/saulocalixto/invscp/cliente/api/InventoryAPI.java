/*
 * Copyright (c) 2018.
 * Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;
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

    protected static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";

    protected static final String PARAMETRO_DE_CONSULTA = "id";

    protected static final String PARAMETRO_DE_DELECAO = "id";

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        InventoryAPI.auth = auth;
    }

    static String chamadaGet(String... urlParameters) throws IOException {
        return chamadaApi(formadorDeUrl(urlParameters),CONSULTA);
    }

    static String chamadaPut(String... urlParameters) throws IOException {
        return chamadaApi(formadorDeUrl(urlParameters), ATUALIZACAO);
    }

    static String chamadaDelete(String... urlParameters) throws IOException {
        return chamadaApi(formadorDeUrl(urlParameters),EXCLUSAO);
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

    private static String chamadaApi(URL url, String metodoRequisicao) throws IOException {
        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(metodoRequisicao);
        conn.setRequestProperty("Autorizacao", auth);
        conn.setRequestProperty("content-type", "application/json");
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
}
