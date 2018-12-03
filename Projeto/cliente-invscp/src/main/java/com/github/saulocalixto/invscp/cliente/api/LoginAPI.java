package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;

public class LoginAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/login";

    private static final String EMAIL = "email";

    private static final String SENHA = "senha";

    public static String login(String email, String senha) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, EMAIL, email, SENHA, senha);
    }
}
