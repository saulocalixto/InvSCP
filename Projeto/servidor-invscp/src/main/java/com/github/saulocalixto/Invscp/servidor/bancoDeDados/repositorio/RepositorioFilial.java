package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.FilialMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioFilial;
import com.github.saulocalixto.Invscp.servidor.negocio.Filial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class RepositorioFilial extends RepositorioPadrao<Filial> implements IRepositorioFilial{

    public Filial Consultar(String id) {
        String sql = "SELECT * FROM Filial WHERE id = ?";
        Filial filial = new Filial();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                filial.setNomeDaFilial(rs.getString(FilialMap.nomeDaFilial));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return filial;
    }

    public List<Filial> ConsultarLista() {
        return null;
    }

    public void Salvar(Filial objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
                FilialMap.nomeTabela,
                FilialMap.id,
                FilialMap.nomeDaFilial);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getNomeDaFilial());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Filial objeto) {
            Excluir(objeto.getId());
            Salvar(objeto);
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                FilialMap.nomeTabela,
                id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    @Override
    public Filial consultarPorNome(String nomeDaFilial) {
        String sql = "SELECT * FROM Filial WHERE nomeDaFilial = ?";
        Filial filial = new Filial();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, nomeDaFilial);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                filial.setId(rs.getString(FilialMap.id));
                filial.setNomeDaFilial(rs.getString(FilialMap.nomeDaFilial));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return filial;
    }

}
