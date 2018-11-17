package com.github.saulocalixto.Invscp.servidor.utilitarios;

import com.github.saulocalixto.Invscp.servidor.enumeradores.EnumGrupoDeAcesso;

public abstract class UtilitarioDaSessao {
    private static String tokenAcesso;
    private static  EnumGrupoDeAcesso permissaoDeUsuarioLogado;

    public static void setTokenAcesso(String token) {
        tokenAcesso = token;
    }

    public static void setPermissaoDeUsuarioLogado(EnumGrupoDeAcesso grupo) {
        permissaoDeUsuarioLogado = grupo;
    }

    public static EnumGrupoDeAcesso retornePermissaoDeUsuarioLogado() {
        return permissaoDeUsuarioLogado;
    }
}
