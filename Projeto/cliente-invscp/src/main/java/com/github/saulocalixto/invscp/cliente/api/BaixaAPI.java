package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

public class BaixaAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/baixa";

    private static final String PARAMETRO_DE_CONSULTA = "id";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    private static final String ID = "id";
    
    private static final String ID_BEM = "idBem";
    
    private static final String DATA = "data";
    
    private static final String OBSERVACAO = "observacao";

    public static String getBaixa(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getBaixas() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String deletarBaixa(String id) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id);
    }
    
    public static String criaBaixa(String id, String idBem, String data, 
            String observacao) {
        return chamadaPut(ENDPOINT_PADRAO, ID, id, ID_BEM, idBem,
            OBSERVACAO, observacao, DATA, data, "motivo", "VENDIDO");
    }
    
    public static String editarBaixa(String id, String idBem, String data, 
            String observacao) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, ID_BEM, idBem,
            OBSERVACAO, observacao, DATA, data, "motivo", "VENDIDO");
    }
}
