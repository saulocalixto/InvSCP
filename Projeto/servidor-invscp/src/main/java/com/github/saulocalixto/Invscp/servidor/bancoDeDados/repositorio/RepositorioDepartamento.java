package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.DepartamentoMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioDepartamento;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDepartamento extends RepositorioPadrao<Departamento> implements IRepositorioDepartamento {

    public Departamento Consultar(String id) {
        String sql = "SELECT * FROM Departamento WHERE id = ?";
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

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return departamento;
    }

    public List<Departamento> ConsultarLista() {

        List<Departamento> listaDepartamento = new ArrayList<Departamento>();
        String sql = String.format("SELECT  * FROM %s", DepartamentoMap.nomeTabela);

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setNomeDoDepartamento(rs.getString(DepartamentoMap.nomeDoDepartamento));
                departamento.setId(rs.getString(DepartamentoMap.id));
                listaDepartamento.add(departamento);
            }
            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return listaDepartamento;
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
        ExcluirPadrao(DepartamentoMap.nomeTabela, id);
    }

    @Override
    public Boolean departamentoTemChefe(String idDepartamento) {
        String sql = String.format("SELECT %s FROM %s as dep, %s as us " +
                "WHERE dep.%s = '%s' AND dep.%s = us.%s AND us.%s = 'ADMINISTRADOR_DEPARTAMENTO'",
                DepartamentoMap.id, DepartamentoMap.nomeTabela, UsuarioMap.nomeTabela, DepartamentoMap.id,
                idDepartamento, DepartamentoMap.id, UsuarioMap.id, UsuarioMap.grupo);
        return verificaSeNaoRetornaResultados(sql);
    }

    @Override
    public Boolean departamentoExiste(String id) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DepartamentoMap.id,
                DepartamentoMap.nomeTabela,
                DepartamentoMap.id,
                id);

        return !verificaSeNaoRetornaResultados(sql);
    }
}
