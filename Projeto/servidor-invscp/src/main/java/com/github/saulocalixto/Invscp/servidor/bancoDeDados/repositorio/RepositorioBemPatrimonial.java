package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositorioBemPatrimonial extends RepositorioPadrao<BemPatrimonial> implements IRepositorioBemPatrimonial {
    public BemPatrimonial Consultar(String id) {
        String sql = "SELECT * FROM BemPatrimonial WHERE id = ?";
        BemPatrimonial bemPatrimonial = new BemPatrimonial();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                bemPatrimonial.setNomeDaBemPatrimonial(rs.getString(BemPatrimonialMap.nomeDaBemPatrimonial));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return bemPatrimonial;
    }

    public List<BemPatrimonial> ConsultarLista() {
        return null;
    }

    public void Salvar(BemPatrimonial objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
                BemPatrimonialMap.nomeTabela,
                BemPatrimonialMap.id,
                BemPatrimonialMap.nomeDaBemPatrimonial);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getNomeDaBemPatrimonial());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(BemPatrimonial objeto) {
        Excluir(objeto.getId());
        Salvar(objeto);
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                BemPatrimonialMap.nomeTabela,
                id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
