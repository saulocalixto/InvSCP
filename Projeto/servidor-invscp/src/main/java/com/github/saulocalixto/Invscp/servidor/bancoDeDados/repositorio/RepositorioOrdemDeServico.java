package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.OrdemDeServicoMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioOrdemDeServico;
import com.github.saulocalixto.Invscp.servidor.negocio.ordemDeServico.OrdemDeServico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RepositorioOrdemDeServico extends RepositorioPadrao<OrdemDeServico> implements IRepositorioOrdemDeServico {

    public OrdemDeServico Consultar(String id) {
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'",
                OrdemDeServicoMap.nomeTabela,
                OrdemDeServicoMap.id,
                id);
        return ConsulteOrdemDeServico(sql);
    }

    public List<OrdemDeServico> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", OrdemDeServicoMap.nomeTabela);

        List<OrdemDeServico> listaOS = new ArrayList<OrdemDeServico>();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                OrdemDeServico ordemDeServico = new OrdemDeServico();
                PreencheOrdemDeServico(ordemDeServico, rs);
                listaOS.add(ordemDeServico);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return listaOS;
    }

    public void Salvar(OrdemDeServico objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?)",
                OrdemDeServicoMap.nomeTabela,
                OrdemDeServicoMap.id,
                OrdemDeServicoMap.identificadorDaOS,
                OrdemDeServicoMap.bem,
                OrdemDeServicoMap.motivo,
                OrdemDeServicoMap.observacao,
                OrdemDeServicoMap.dataAbertura,
                OrdemDeServicoMap.dataEncerramento,
                OrdemDeServicoMap.nomeDaPrestadora,
                OrdemDeServicoMap.situacao);


        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getIdentificadorDaOS());
            stmt.setString(3,objeto.getBem());
            stmt.setString(4,objeto.getMotivo());
            stmt.setString(5,objeto.getObservacao());
            stmt.setString(6, objeto.getDataAbertura());
            stmt.setString(7,objeto.getDataEncerramento());
            stmt.setString(8,objeto.getNomeDaPrestadora());
            stmt.setString(9,objeto.getSituacao().name());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(OrdemDeServico objeto) {
        String sql = String.format("UPDATE %s " +
                        "SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' " +
                        "WHERE %s = '%s'",
                OrdemDeServicoMap.nomeTabela,
                OrdemDeServicoMap.identificadorDaOS,
                objeto.getIdentificadorDaOS(),
                OrdemDeServicoMap.bem,
                objeto.getBem(),
                OrdemDeServicoMap.motivo,
                objeto.getMotivo(),
                OrdemDeServicoMap.observacao,
                objeto.getObservacao(),
                OrdemDeServicoMap.dataAbertura,
                objeto.getDataAbertura(),
                OrdemDeServicoMap.dataEncerramento,
                objeto.getDataEncerramento(),
                OrdemDeServicoMap.nomeDaPrestadora,
                objeto.getNomeDaPrestadora(),
                OrdemDeServicoMap.situacao,
                objeto.getSituacao().name(),
                OrdemDeServicoMap.id,
                objeto.getId());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                OrdemDeServicoMap.nomeTabela,
                id);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    private OrdemDeServico ConsulteOrdemDeServico(String sql) {
        OrdemDeServico ordemDeServico = new OrdemDeServico();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                PreencheOrdemDeServico(ordemDeServico, rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            System.out.println("Erro de leitura.");
        }
        return ordemDeServico;
    }

    private void PreencheOrdemDeServico(OrdemDeServico ordemDeServico, ResultSet rs) throws SQLException {
        ordemDeServico.setId(rs.getString(OrdemDeServicoMap.id));
        ordemDeServico.setIdentificadorDaOS(rs.getString(OrdemDeServicoMap.identificadorDaOS));
        ordemDeServico.setBem(rs.getString(OrdemDeServicoMap.bem));
        ordemDeServico.setDataAbertura(rs.getString(OrdemDeServicoMap.dataAbertura));
        ordemDeServico.setDataEncerramento((rs.getString(OrdemDeServicoMap.dataEncerramento)));
        ordemDeServico.setMotivo(rs.getString(OrdemDeServicoMap.motivo));
        ordemDeServico.setNomeDaPrestadora(rs.getString(OrdemDeServicoMap.nomeDaPrestadora));
        ordemDeServico.setObservacao(rs.getString(OrdemDeServicoMap.observacao));
        ordemDeServico.setSituacao(rs.getString(OrdemDeServicoMap.situacao));
    }

}
