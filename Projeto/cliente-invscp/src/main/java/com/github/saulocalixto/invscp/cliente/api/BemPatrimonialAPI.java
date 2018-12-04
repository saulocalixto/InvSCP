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
    private static final String SITUACAO = "status";
    private static final String GRUPO_DE_MATERIAL = "grupoDeMaterial";

    public static String getBemParimonial(String id) {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, id);
    }
    
    public static String getBemParimoniais() {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }

    public static String deletarBemParimonial(String id) {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, id);
    }
    
    public static String criaBemParimonial(
            String localAtual, String numeroDeTombamento, String denominacao, String dataDeAquisicao,
            String especificacao, String garantia, String marca, String valorDeCompra, String situacao,
            String notaFiscal, String grupoDeMaterial) {
        return chamadaPut(ENDPOINT_PADRAO,
                NUMERO_TOMBAMENTO, numeroDeTombamento,
                LOCAL_ATUAL, localAtual,
                DENOMINACAO, denominacao,
                DATA_AQUISICAO, dataDeAquisicao,
                ESPECIFICACAO, especificacao,
                GARANTIA, garantia,
                MARCA, marca,
                VALOR_COMPRA, valorDeCompra,
                SITUACAO, situacao,
                NOTA_FISCAL, notaFiscal,
                GRUPO_DE_MATERIAL, grupoDeMaterial);
    }

    public static String editaBemParimonial(
            String id, String localAtual, String numeroDeTombamento, String denominacao, String dataDeAquisicao,
            String especificacao, String garantia, String marca, String valorDeCompra, String situacao,
            String notaFiscal, String grupoDeMaterial) {
        return chamadaPut(ENDPOINT_PADRAO,
                ID, id,
                NUMERO_TOMBAMENTO, numeroDeTombamento,
                LOCAL_ATUAL, localAtual,
                DENOMINACAO, denominacao,
                DATA_AQUISICAO, dataDeAquisicao,
                ESPECIFICACAO, especificacao,
                GARANTIA, garantia,
                MARCA, marca,
                VALOR_COMPRA, valorDeCompra,
                SITUACAO, situacao,
                NOTA_FISCAL, notaFiscal,
                GRUPO_DE_MATERIAL, grupoDeMaterial);
    }
}
