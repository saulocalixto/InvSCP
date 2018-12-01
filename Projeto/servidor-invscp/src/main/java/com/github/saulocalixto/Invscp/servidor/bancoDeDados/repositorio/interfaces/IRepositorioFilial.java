package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces;

import com.github.saulocalixto.Invscp.servidor.negocio.Filial;

public interface IRepositorioFilial extends IRepositorio<Filial> {
    Filial consultarPorNome(String nomeDaFilial);
}
