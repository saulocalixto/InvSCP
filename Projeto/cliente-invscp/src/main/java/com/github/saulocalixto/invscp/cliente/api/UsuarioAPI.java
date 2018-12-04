package com.github.saulocalixto.invscp.cliente.api;

import java.io.IOException;

public class UsuarioAPI extends InventoryAPI {

    private static final String ENDPOINT_PADRAO = "/usuario";

    private static final String PARAMETRO_DE_CONSULTA = "email";

    private static final String ENDPOINT_CONSULTE_TODOS = ENDPOINT_PADRAO + "/consulteTodos";
    
    private static final String ENDPOINT_ATUALIZE = ENDPOINT_PADRAO + "/atualize";

    private static final String ID = "id";
    
    private static final String EMAIL = "email";
    
    private static final String SENHA = "senha";
    
    private static final String NOME = "nome";
    
    private static final String CPF = "cpf";
    
    private static final String ID_DEPARTAMENTO = "departamento";
    
    private static final String GRUPO = "grupo";

    public static String getUsuario(String email) throws IOException {
        return chamadaGet(ENDPOINT_PADRAO, PARAMETRO_DE_CONSULTA, email);
    }

    public static String deletarUsuario(String email) throws IOException {
        return chamadaDelete(ENDPOINT_PADRAO, PARAMETRO_DE_DELECAO, email);
    }
    
    public static String getUsuarios() throws IOException {
        return chamadaGet(ENDPOINT_CONSULTE_TODOS);
    }
    
    public static String criaUsuario(String email, String senha, String nome, 
            String cpf, String idDepartamento, String grupo) {
        return chamadaPut(ENDPOINT_PADRAO, EMAIL, email, SENHA, senha,
            NOME, nome, CPF, cpf, GRUPO, grupo, ID_DEPARTAMENTO, idDepartamento);
    }
    
    public static String editarUsuario(String id, String email, String senha, String nome, 
            String cpf, String idDepartamento, String grupo) {
        return chamadaPut(ENDPOINT_ATUALIZE, ID, id, EMAIL, email, SENHA, senha,
            NOME, nome, CPF, cpf, GRUPO, grupo, ID_DEPARTAMENTO, idDepartamento);
    }
}
