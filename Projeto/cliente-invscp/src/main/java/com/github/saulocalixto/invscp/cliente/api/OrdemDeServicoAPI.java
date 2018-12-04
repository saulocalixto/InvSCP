package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

public class OrdemDeServicoAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/ordemDeServico";

    private static final String PARAMETRO_DE_CONSULTA = "id";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    public static final String ID = "id";
    
    public static final String IDENTIFICADOR_ORDEM = "identificadorDaOS";
    
    public static final String BEM_PATRIMONIAL = "bem";
    
    public static final String MOTIVO = "motivo";
    
    public static final String OBSERVACAO = "observacao";
    
    public static final String DATA_ABERTURA = "dataAbertura";
    
    public static final String DATA_ENCERRAMENTO = "dataEncerramento";
    
    public static final String NOME_PRESTADORA = "nomeDaPrestadora";
    
    public static final String SITUACAO = "situacao";

    public static String getOrdemDeServico(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getOrdensDeServico() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String deletarOrdemDeServico(String id) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id);
    }
    
    public static String criaOrdemDeServico(
            String id, 
            String identificadorOrdem,
            String idBem,
            String motivo,
            String observacao,
            String dataAbertura,
            String dataEncerramento,
            String nomePrestadora) {
        return chamadaPut(ENDPOINT_PADRAO, 
                ID, id,
                IDENTIFICADOR_ORDEM, identificadorOrdem,
                BEM_PATRIMONIAL, idBem,
                MOTIVO, motivo,
                OBSERVACAO, observacao,
                DATA_ABERTURA, dataAbertura,
                DATA_ENCERRAMENTO, dataEncerramento,
                NOME_PRESTADORA, nomePrestadora);
    }
    
    public static String editarOrdemDeServico(
            String id, 
            String identificadorOrdem,
            String idBem,
            String motivo,
            String observacao,
            String dataAbertura,
            String dataEncerramento,
            String nomePrestadora) {
        return chamadaPut(ENDPOINT_ATUALIZE, 
                ID, id,
                IDENTIFICADOR_ORDEM, identificadorOrdem,
                BEM_PATRIMONIAL, idBem,
                MOTIVO, motivo,
                OBSERVACAO, observacao,
                DATA_ABERTURA, dataAbertura,
                DATA_ENCERRAMENTO, dataEncerramento,
                NOME_PRESTADORA, nomePrestadora);
    }
}
