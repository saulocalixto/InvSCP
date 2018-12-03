package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;


import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.SalaMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioSala;
import com.github.saulocalixto.Invscp.servidor.negocio.sala.Sala;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioSala extends RepositorioPadrao<Sala> implements IRepositorioSala{

    public Sala Consultar(String id) {
        String sql = String.format("SELECT * FROM %s where %s = '%s'", SalaMap.nomeTabela, SalaMap.id, id);
        Sala sala = new Sala();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                sala = PreencheSala(rs);
            }
            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return sala;
    }

    public List<Sala> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", SalaMap.nomeTabela);
        List<Sala> listaSala = new ArrayList<>();

        PreencheListaDeSalas(sql, listaSala);

        return listaSala;
    }

    public void Salvar(Sala objeto) {
        String sql = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (%s, %s, %s, %s)",
                SalaMap.nomeTabela,
                SalaMap.id,
                SalaMap.numeroSala,
                SalaMap.departamentoAQuePertence,
                SalaMap.predioEmQueEstaLocalizada,
                objeto.getId(),
                objeto.getNumeroSala(),
                objeto.getIdDepartamento(),
                objeto.getIdPredio());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Sala objeto) {
        String sql = String
                .format("UPDATE %s SET %s = '%s', %s = '%s', %s = %s WHERE %s = '%s'",
                        SalaMap.nomeTabela,
                        SalaMap.predioEmQueEstaLocalizada,
                        objeto.getIdPredio(),
                        SalaMap.departamentoAQuePertence,
                        objeto.getIdDepartamento(),
                        SalaMap.numeroSala,
                        objeto.getNumeroSala(),
                        SalaMap.id,
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
        ExcluirPadrao(SalaMap.nomeTabela, id);
    }

    @Override
    public void atualizarDepartamento(String id, String idDepartamento) {
        String sql = String.format("UPDATE %s SET %s = %s WHERE %s = %s",
                SalaMap.nomeTabela,
                SalaMap.departamentoAQuePertence,
                idDepartamento,
                SalaMap.id,
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
    public void atualizarPredio(String id, String idPredio) {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SalaMap.nomeTabela,
                SalaMap.predioEmQueEstaLocalizada,
                SalaMap.id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setObject(1, idPredio);
            stmt.setObject(2, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    @Override
    public List<Sala> consulteSalasDeDepartamento(String idDepartamento) {
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'    ",
                SalaMap.nomeTabela,
                SalaMap.departamentoAQuePertence,
                idDepartamento);
        List<Sala> listaSalas = new ArrayList<>();
        PreencheListaDeSalas(sql, listaSalas);

        return listaSalas;
    }

    @Override
    public List<Sala> consulteSalasDePredio(String idPredio) {
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'    ",
                SalaMap.nomeTabela,
                SalaMap.predioEmQueEstaLocalizada,
                idPredio);
        List<Sala> listaSalas = new ArrayList<>();

        PreencheListaDeSalas(sql, listaSalas);

        return listaSalas;
    }

    @Override
    public Boolean numeroDaSalaNaoSeRepeteNoPredio(int numeroSala, String idPredio) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = %s",
                SalaMap.id,
                SalaMap.nomeTabela,
                SalaMap.predioEmQueEstaLocalizada,
                idPredio,
                SalaMap.numeroSala,
                numeroSala);

        return verificaSeNaoRetornaResultados(sql);
    }

    private void PreencheListaDeSalas(String sql, List<Sala> listaSalas) {
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sala sala = PreencheSala(rs);
                listaSalas.add(sala);
            }
            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    private Sala PreencheSala(ResultSet rs) throws SQLException {
        Sala sala = new Sala();
        sala.setNumeroSala(rs.getInt(SalaMap.numeroSala));
        sala.setId(rs.getString(SalaMap.id));
        sala.setIdDepartamento(rs.getString(SalaMap.departamentoAQuePertence));
        sala.setIdPredio(rs.getString(SalaMap.predioEmQueEstaLocalizada));
        return sala;
    }
}