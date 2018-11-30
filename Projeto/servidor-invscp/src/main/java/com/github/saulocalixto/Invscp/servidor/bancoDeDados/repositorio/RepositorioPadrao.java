package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RepositorioPadrao<T> implements IRepositorio<T> {
    @Override
    public Connection RetorneConexaoBd() {
        return ConexaoBd.getConexaoMySQL();
    }

    protected Boolean verificaSeRetornaResultados(String sql) {
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return false;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return true;
    }

    protected void ExecutaQuery(String sql) throws SQLException {
        PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
        stmt.execute();
        stmt.close();
    }
}
