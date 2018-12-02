package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.EnderecoMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.PredioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioPredio;
import com.github.saulocalixto.Invscp.servidor.negocio.Predio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPredio extends RepositorioPadrao<Predio> implements IRepositorioPredio {

    public Predio Consultar(String id) {
        String sql = String.format("SELECT * FROM %s where %s = '%s'", PredioMap.nomeTabela, PredioMap.id, id);
        Predio predio = new Predio();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                preenchePredio(predio, rs);
            }

            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return predio;
    }

    public List<Predio> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", PredioMap.nomeTabela);
        List<Predio> listaPredio = new ArrayList<>();

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Predio predio = new Predio();
                preenchePredio(predio, rs);
                listaPredio.add(predio);
            }

            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return listaPredio;
    }

    public void Salvar(Predio objeto) {
        String sql = String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', '%s')",
                PredioMap.nomeTabela,
                PredioMap.id,
                PredioMap.nome,
                PredioMap.endereco,
                objeto.getId(),
                objeto.getNome(),
                objeto.getEndereco().getId());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Predio objeto) {
        String sql = String
                .format("UPDATE %s SET %s = ?, %s = ? = '%s' WHERE %s = '%s'",
                        PredioMap.nomeTabela,
                        PredioMap.nome,
                        PredioMap.endereco,
                        PredioMap.id,
                        objeto.getId());



        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setObject(1, objeto.getNome(), Types.VARCHAR);
            stmt.setObject(2, objeto.getId(), Types.VARCHAR);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Excluir(String id) {
        ExcluirPadrao(EnderecoMap.nomeTabela, id);
    }

    private void preenchePredio(Predio predio, ResultSet rs) throws SQLException {
        predio.setNome(rs.getString(PredioMap.nome));
        predio.setId(rs.getString(PredioMap.id));
    }
}
