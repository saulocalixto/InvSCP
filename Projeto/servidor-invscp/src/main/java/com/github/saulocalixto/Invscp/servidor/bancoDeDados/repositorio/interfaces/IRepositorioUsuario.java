package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

public interface IRepositorioUsuario extends IRepositorio<Usuario> {
    Usuario consultarPorEmail(String email);
}
