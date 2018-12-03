package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;

public class UsuarioAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/usuario";

    private static final String PARAMETRO_DE_CONSULTA = "email";

    public static String getUsuario(String email) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, email);
    }

    public static String deletarUsuario(String email) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, email);
    }
}
