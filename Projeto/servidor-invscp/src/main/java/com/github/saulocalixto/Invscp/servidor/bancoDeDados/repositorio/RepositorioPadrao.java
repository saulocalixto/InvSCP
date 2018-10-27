package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import java.sql.Connection;

public abstract class RepositorioPadrao<T> implements IRepositorio<T> {
    @Override
    public Connection RetorneConexaoBd() {
        return ConexaoBd.getConexaoMySQL();
    }
}
