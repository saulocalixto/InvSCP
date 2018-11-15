package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;

import java.util.List;

public interface IRepositorioUsuario extends IRepositorio<Usuario> {
    Usuario consultarPorEmail(String email);
}
