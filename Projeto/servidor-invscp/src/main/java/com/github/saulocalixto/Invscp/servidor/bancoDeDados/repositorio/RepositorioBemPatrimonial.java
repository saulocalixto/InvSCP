package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.BemPatrimonialMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBemPatrimonial;
import com.github.saulocalixto.Invscp.servidor.negocio.bemPatrimonial.BemPatrimonial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioBemPatrimonial extends RepositorioPadrao<BemPatrimonial> implements IRepositorioBemPatrimonial {

    public BemPatrimonial Consultar(String id) {
        String sql = String.format("SELECT * FROM %s WHERE %s = %s",
                BemPatrimonialMap.nomeTabela,
                BemPatrimonialMap.id,
                id);
        BemPatrimonial bemPatrimonial = new BemPatrimonial();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bemPatrimonial = PreencheBemPatrimonial(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return bemPatrimonial;
    }

    public List<BemPatrimonial> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", BemPatrimonialMap.nomeTabela);
        List<BemPatrimonial> listaBemPatrimonial = new ArrayList<>();

        PreencheListaDeBensPatrimoniais(sql, listaBemPatrimonial);

        return listaBemPatrimonial;
    }

    public void Salvar(BemPatrimonial objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                BemPatrimonialMap.nomeTabela,
                BemPatrimonialMap.id,
                BemPatrimonialMap.numeroDeTombamento,
                BemPatrimonialMap.localAtual,
                BemPatrimonialMap.denominacao,
                BemPatrimonialMap.dataDeAquisicao,
                BemPatrimonialMap.especificacao,
                BemPatrimonialMap.garantia,
                BemPatrimonialMap.marca,
                BemPatrimonialMap.valorDeCompra,
                BemPatrimonialMap.situacao,
                BemPatrimonialMap.notaFiscal,
                BemPatrimonialMap.grupoDeMaterial,
                BemPatrimonialMap.ordemDeServico);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getNumeroDeTombamento());
            stmt.setString(3, objeto.getLocalAtual());
            stmt.setString(4, objeto.getDenominacao());
            stmt.setString(5, objeto.getDataDeAquisicao());
            stmt.setString(6, objeto.getEspecificacao());
            stmt.setString(7, objeto.getGarantia());
            stmt.setString(8, objeto.getMarca());
            stmt.setDouble(9, objeto.getValorDeCompra());
            stmt.setString(10, objeto.getStatus().name());
            stmt.setString(11, objeto.getNotaFiscal());
            stmt.setString(12, objeto.getGrupoDeMaterial().name());
            stmt.setString(13, objeto.getOrdemDeServico());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(BemPatrimonial objeto) {
        String sql = String
                .format("UPDATE %s SET %s = '%s', %s = '%s', %s = %s, %s = '%s', %s = '%s' WHERE %s = '%s'",
                        BemPatrimonialMap.nomeTabela,
                        BemPatrimonialMap.localAtual,
                        objeto.getLocalAtual(),
                        BemPatrimonialMap.denominacao,
                        objeto.getDenominacao(),
                        BemPatrimonialMap.especificacao,
                        objeto.getEspecificacao(),
                        BemPatrimonialMap.situacao,
                        objeto.getStatus().name(),
                        BemPatrimonialMap.ordemDeServico,
                        objeto.getOrdemDeServico(),
                        BemPatrimonialMap.id,
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
        ExcluirPadrao(BemPatrimonialMap.nomeTabela, id);
    }

    private BemPatrimonial PreencheBemPatrimonial(ResultSet rs) throws SQLException {
        BemPatrimonial bemPatrimonial = new BemPatrimonial();
        bemPatrimonial.setId(rs.getString(BemPatrimonialMap.id));
        bemPatrimonial.setNumeroDeTombamento(rs.getString(BemPatrimonialMap.numeroDeTombamento));
        bemPatrimonial.setLocalAtual(rs.getString(BemPatrimonialMap.localAtual));
        bemPatrimonial.setDenominacao(rs.getString(BemPatrimonialMap.denominacao));
        bemPatrimonial.setDataDeAquisicao(rs.getString(BemPatrimonialMap.dataDeAquisicao));
        bemPatrimonial.setEspecificacao(rs.getString(BemPatrimonialMap.especificacao));
        bemPatrimonial.setGarantia(rs.getString(BemPatrimonialMap.garantia));
        bemPatrimonial.setMarca(rs.getString(BemPatrimonialMap.marca));
        bemPatrimonial.setValorDeCompra(rs.getDouble(BemPatrimonialMap.valorDeCompra));
        bemPatrimonial.setNotaFiscal(rs.getString(BemPatrimonialMap.notaFiscal));
        bemPatrimonial.setGrupoDeMaterial(rs.getString(BemPatrimonialMap.grupoDeMaterial));
        bemPatrimonial.setStatus(rs.getString(BemPatrimonialMap.situacao));
        bemPatrimonial.setOrdemDeServico(rs.getString(BemPatrimonialMap.ordemDeServico));
        return bemPatrimonial;
    }

    private void PreencheListaDeBensPatrimoniais(String sql, List<BemPatrimonial> listaBensPatrimoniais) {
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BemPatrimonial bemPatrimonial = PreencheBemPatrimonial(rs);
                listaBensPatrimoniais.add(bemPatrimonial);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
