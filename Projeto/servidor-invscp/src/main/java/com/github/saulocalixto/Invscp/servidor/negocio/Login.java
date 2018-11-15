package com.github.saulocalixto.Invscp.servidor.negocio;

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
}
