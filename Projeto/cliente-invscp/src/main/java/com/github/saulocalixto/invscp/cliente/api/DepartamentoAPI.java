package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class DepartamentoAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/departamento";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    private static final String ID = "id";
    
    private static final String NOME_DEPARTAMENTO = "nome";

    public static String getDepartamento(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getDepartamentos() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String criaDepartamento(String id, String nome) {
        return chamadaPut(ENDPOINT_PADRAO, NOME_DEPARTAMENTO, nome);
    }
    
    public  static String deletaDepartamento(String id, String nome) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id, nome);
    }
    
    public static String editarDepartamento(String id, String nome) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, NOME_DEPARTAMENTO, nome);
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
    
    private static String chamadaApi(URL url, String metodoRequisicao, String... body) throws IOException {
        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(metodoRequisicao);
        conn.setRequestProperty("Autorizacao", getAuth());
        conn.setRequestProperty("content-type", "application/json");
        prepareBody(conn, body);
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode < 200 || responseCode > 299) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
        else if (conn.getContentLength() == 54) {
            throw new RuntimeException("Resposta inválida do servidor. Content length: " + conn.getContentLength());
        } else {
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
            JSONArray arr = new JSONArray();
            JSONObject arrObjs = new JSONObject();
            for (int i = 0; i < body.length; i += 2){
                if (i > 1) {
                    arr.put(arrObjs.put(body[i], body[i+1]));
                } else {
                    json.put(body[i], body[i+1]);
                }
            }
            if (!arr.isEmpty()) {
                json.put("listaDeSalas", arr);
            }
            byte[] outputInBytes = json.toString().getBytes("UTF-8");
            os.write( outputInBytes );
            os.close();
        } catch (Exception ignored) {}
    }
}
