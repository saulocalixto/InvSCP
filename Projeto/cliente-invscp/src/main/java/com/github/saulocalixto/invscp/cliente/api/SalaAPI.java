package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;

public class SalaAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/sala";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";

    private static final String ID = "id";

    private static final String NUMERO_SALA = "numeroSala";

    private static final String ID_DEPARTAMENTO = "idDepartamento";

    private static final String ID_PREDIO = "idPredio";

    public static String getSala(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getSalas() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String criaSala(String numeroSala, String idDepartamento, String idPredio) {
        try {
            return chamadaPut(ENDPOINT_PADRAO, NUMERO_SALA, numeroSala,
                    ID_DEPARTAMENTO, idDepartamento, ID_PREDIO, idPredio);
        } catch (IOException e) {
            return "{}";
        }
    }
}
