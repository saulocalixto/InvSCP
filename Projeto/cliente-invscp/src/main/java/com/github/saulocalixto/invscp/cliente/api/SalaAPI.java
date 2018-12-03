package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;

public class SalaAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/sala";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    public static String getSala(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getSalas() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }
}
