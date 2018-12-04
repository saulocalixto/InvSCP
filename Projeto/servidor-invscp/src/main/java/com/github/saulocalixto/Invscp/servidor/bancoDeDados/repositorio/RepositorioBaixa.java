package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.BaixaMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioBaixa;
import com.github.saulocalixto.Invscp.servidor.negocio.baixa.Baixa;

import java.text.ParseException;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RepositorioBaixa extends RepositorioPadrao<Baixa> implements IRepositorioBaixa {

    public Baixa Consultar(String id) {
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'",
                BaixaMap.nomeTabela,
                BaixaMap.id,
                id);
        return ConsulteBaixa(sql);
    }

    public List<Baixa> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", BaixaMap.nomeTabela);

        List<Baixa> listaBaixa = new ArrayList<Baixa>();

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Baixa baixa = new Baixa();
                PreencheBaixa(baixa, rs);
                listaBaixa.add(baixa);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return listaBaixa;
    }

    public void Salvar(Baixa objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
                BaixaMap.nomeTabela,
                BaixaMap.id,
                BaixaMap.idBem,
                BaixaMap.data,
                BaixaMap.observacao,
                BaixaMap.motivo);


        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getIdBem());
            stmt.setString(3,converteData(objeto.getData()));
            stmt.setString(4,objeto.getObservacao());
            stmt.setInt(5, objeto.getMotivo().ordinal());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Baixa objeto) {
        String sql = String.format("UPDATE %s " +
                        "SET %s = '%s', %s = '%s', %s = '%s', %s = '%s'" +
                        "WHERE %s = '%s'",
                BaixaMap.nomeTabela,
                BaixaMap.idBem,
                objeto.getIdBem(),
                BaixaMap.data,
                converteData(objeto.getData()),
                BaixaMap.observacao,
                objeto.getObservacao(),
                BaixaMap.motivo,
                objeto.getMotivo().ordinal(),
                BaixaMap.id,
                objeto.getId());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                BaixaMap.nomeTabela,
                id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    private Baixa ConsulteBaixa(String sql) {
        Baixa baixa = new Baixa();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                PreencheBaixa(baixa, rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            System.out.println("Erro de leitura.");
        }
        return baixa;
    }

    private void PreencheBaixa(Baixa baixa, ResultSet rs) throws SQLException {
        baixa.setId(rs.getString(BaixaMap.id));
        baixa.setIdBem(rs.getString((BaixaMap.idBem)));
        baixa.setData(converteData(rs.getString(BaixaMap.data)));
        baixa.setObservacao(rs.getString(BaixaMap.observacao));
        baixa.setMotivo(rs.getString(BaixaMap.motivo));
    }

    private String converteData(Date data) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String dataConvertida = format.format(data);
        return dataConvertida;
    }

    private Date converteData(String data) {
        try {
            String dataString = data;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = format.parse(dataString);
            return date;
        } catch (ParseException e) {
            return new Date();
        }
    }

}
