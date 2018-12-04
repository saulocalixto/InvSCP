package com.github.saulocalixto.invscp.cliente.api;

import static com.github.saulocalixto.invscp.cliente.api.InventoryAPI.chamadaGet;
import java.io.IOException;

public class BemPatrimonialAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/bemPatrimonial";

    private static final String PARAMETRO_DE_CONSULTA = "id";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    public static final String ID = "id";
    
    public static final String NUMERO_TOMBAMENTO = "numeroDeTombamento";
    
    public static final String LOCAL_ATUAL = "localAtual";
    
    public static final String DENOMINACAO = "denominacao";
    
    public static final String DATA_AQUISICAO = "dataDeAquisicao";
    
    public static final String ESPECIFICACAO = "especificacao";
    
    public static final String GARANTIA = "garantia";
    
    public static final String MARCA = "marca";
    
    public static final String VALOR_COMPRA = "valorDeCompra";
    
    public static final String NOTA_FISCAL = "notaFiscal";
    
    public static final String ORDEM_DE_SERVICO = "ordemDeServico";

    public static String getBemParimonial(String id) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getBemParimoniais() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String deletarBemParimonial(String id) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id);
    }
    
    public static String criaBemParimonial(
            String id, 
            String numTombamento,
            String localAtual,
            String denomincacao,
            String dataAquisicao,
            String especificacao,
            String garantia,
            String marca,
            String valorCompra,
            String notaFiscal,
            String idOrdemServico) {
        return chamadaPut(ENDPOINT_PADRAO, 
                ID, id,
                NUMERO_TOMBAMENTO, numTombamento,
                LOCAL_ATUAL, localAtual,
                DENOMINACAO, denomincacao,
                DATA_AQUISICAO, dataAquisicao,
                ESPECIFICACAO, especificacao,
                GARANTIA, garantia,
                MARCA, marca,
                VALOR_COMPRA, valorCompra,
                NOTA_FISCAL, notaFiscal,
                ORDEM_DE_SERVICO, idOrdemServico);
    }
    
    public static String editarBemParimonial(
            String id, 
            String numTombamento,
            String localAtual,
            String denomincacao,
            String dataAquisicao,
            String especificacao,
            String garantia,
            String marca,
            String valorCompra,
            String notaFiscal,
            String idOrdemServico) {
        return chamadaPut(ENDPOINT_ATUALIZE, 
                ID, id,
                NUMERO_TOMBAMENTO, numTombamento,
                LOCAL_ATUAL, localAtual,
                DENOMINACAO, denomincacao,
                DATA_AQUISICAO, dataAquisicao,
                ESPECIFICACAO, especificacao,
                GARANTIA, garantia,
                MARCA, marca,
                VALOR_COMPRA, valorCompra,
                NOTA_FISCAL, notaFiscal,
                ORDEM_DE_SERVICO, idOrdemServico);
    }
}
