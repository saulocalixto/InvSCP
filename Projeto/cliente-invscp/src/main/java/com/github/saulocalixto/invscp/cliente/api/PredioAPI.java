package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

public class PredioAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/predio";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    private static final String ID = "id";
    
    private static final String NOME = "nome";

    public static String getPredio(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getPredios() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String criaPredio(String id, String nome) {
        return chamadaPut(ENDPOINT_PADRAO, ID, id, NOME, nome);
    }
    
    public  static String deletaPredio(String id, String nome) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id, nome);
    }
    
    public static String editarPredio(String id, String nome) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, NOME, nome);
    }
}
