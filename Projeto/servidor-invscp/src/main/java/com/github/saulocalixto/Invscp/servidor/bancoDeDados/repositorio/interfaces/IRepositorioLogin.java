package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public interface IRepositorioLogin {
    Boolean tokenValido(String token);
    Login retorneLoginUsuario(String idUsuario);
    void efetuarLogin(Login objeto);
    void deslogar(String tokeAcesso);
    Usuario retorneUsuario(String token);
}
