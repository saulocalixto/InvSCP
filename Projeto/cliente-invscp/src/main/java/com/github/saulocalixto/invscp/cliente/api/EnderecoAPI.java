package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

public class EnderecoAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/endereco";

    private static final String PARAMETRO_DE_CONSULTA = "id";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    private static final String ID = "id";
    
    private static final String RUA = "rua";
    
    private static final String CIDADE = "cidade";
    
    private static final String CEP = "cep";
    
    private static final String BAIRRO = "bairro";

    public static String getEndereco(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getEnderecos() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String deletarEndereco(String id) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id);
    }
    
    public static String criaEndereco(String id, String rua, String bairro, 
            String cidade, String cep) {
        return chamadaPut(ENDPOINT_PADRAO, ID, id, RUA, rua,
            CIDADE, cidade, CEP, cep, BAIRRO, bairro);
    }
    
    public static String editarEndereco(String id, String rua, String bairro, 
            String cidade, String cep) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, RUA, rua,
            CIDADE, cidade, CEP, cep, BAIRRO, bairro);
    }
}
