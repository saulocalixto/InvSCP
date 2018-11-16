package com.github.saulocalixto.Invscp.servidor.negocio;

import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class Login extends ModelPadrao {

    private Usuario usuario;
    private String tokenAcesso;

    public Login()
    {
        tokenAcesso = getRandomID();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTokenAcesso() {
        return tokenAcesso;
    }

    public void setTokenAcesso(String tokenAcesso) {
        this.tokenAcesso = tokenAcesso;
    }

    public void limpaTokenAcesso() {
        this.tokenAcesso = null;
    }
}
