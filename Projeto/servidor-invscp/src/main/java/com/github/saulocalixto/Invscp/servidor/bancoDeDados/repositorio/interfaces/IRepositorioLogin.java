package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.Login;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public interface IRepositorioLogin extends IRepositorio<Login> {
    Boolean tokenValido(String token);
    Login retorneLoginUsuario(String idUsuario);
}
