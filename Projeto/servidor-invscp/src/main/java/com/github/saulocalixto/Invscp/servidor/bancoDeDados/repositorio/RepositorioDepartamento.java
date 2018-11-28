package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.DepartamentoMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.SalaMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.Sala;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositorioDepartamento extends RepositorioPadrao<Departamento> implements IRepositorioDepartamento {

    private IRepositorioSala repositorioSala;

    public Departamento Consultar(String id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Departamento departamento = new Departamento();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                departamento.setNomeDoDepartamento(rs.getString(DepartamentoMap.nomeDoDepartamento));
                departamento.setId(rs.getString(DepartamentoMap.id));
            }
            rs.close();
            stmt.close();

            departamento.setListaDeSalas(RepositorioDeSala().consulteSalasDeDepartamento(id));

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return departamento;
    }

    public List<Departamento> ConsultarLista() {
        return null;
    }

    public void Salvar(Departamento objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s) VALUES(?,?)",
                DepartamentoMap.nomeTabela,
                DepartamentoMap.id,
                DepartamentoMap.nomeDoDepartamento);

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getNomeDoDepartamento());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Departamento objeto) {
        String sql = String.format("UPDATE %s SET %s = %s WHERE %s = %s",
                DepartamentoMap.nomeTabela,
                DepartamentoMap.nomeDoDepartamento,
                DepartamentoMap.id,
                objeto.getId());
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                DepartamentoMap.nomeTabela,
                id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    private IRepositorioSala RepositorioDeSala() {
        return repositorioSala != null
                ? repositorioSala
                : (repositorioSala = new RepositorioSala());
    }
}
