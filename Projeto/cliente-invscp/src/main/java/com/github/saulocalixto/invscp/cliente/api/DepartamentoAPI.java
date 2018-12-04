package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

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
        return chamadaPut(ENDPOINT_PADRAO, ID, id, NOME_DEPARTAMENTO, nome);
    }
    
    public  static String deletaDepartamento(String id, String nome) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id, nome);
    }
    
    public static String editarDepartamento(String id, String nome) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, NOME_DEPARTAMENTO, nome);
    }
}
