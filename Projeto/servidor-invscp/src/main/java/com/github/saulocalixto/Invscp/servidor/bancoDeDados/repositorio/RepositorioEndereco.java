package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.EnderecoMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioEndereco;
import com.github.saulocalixto.Invscp.servidor.negocio.endereco.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEndereco extends RepositorioPadrao<Endereco> implements IRepositorioEndereco {

    public Endereco Consultar(String id) {
        String sql = String.format("SELECT * FROM %s where %s = '%s'", EnderecoMap.nomeTabela, EnderecoMap.id, id);
        Endereco endereco = new Endereco();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                PreencheEndereco(endereco, rs);
            }
            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return endereco;
    }

    public List<Endereco> ConsultarLista() {
        String sql = String.format("SELECT * FROM %s", EnderecoMap.nomeTabela);
        List<Endereco> listaEndereco = new ArrayList<>();

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                PreencheEndereco(endereco, rs);
                listaEndereco.add(endereco);
            }

            rs.close();
            stmt.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return listaEndereco;
    }

    public void Salvar(Endereco objeto) {

        String sql = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s')",
                EnderecoMap.nomeTabela,
                EnderecoMap.bairro,
                EnderecoMap.cep,
                EnderecoMap.cidade,
                EnderecoMap.rua,
                EnderecoMap.id,
                objeto.getBairro(),
                objeto.getCep(),
                objeto.getCidade(),
                objeto.getRua(),
                objeto.getId());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

    }

    public void Atualizar(Endereco objeto) {
        String sql = String
                .format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s'",
                        EnderecoMap.nomeTabela,
                        EnderecoMap.rua,
                        objeto.getRua(),
                        EnderecoMap.cidade,
                        objeto.getCidade(),
                        EnderecoMap.cep,
                        objeto.getCep(),
                        EnderecoMap.bairro,
                        objeto.getBairro(),
                        EnderecoMap.id,
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
        ExcluirPadrao(EnderecoMap.nomeTabela, id);
    }

    private void PreencheEndereco(Endereco endereco, ResultSet rs) throws SQLException {
        endereco.setBairro(rs.getString(EnderecoMap.bairro));
        endereco.setCep(rs.getString(EnderecoMap.cep));
        endereco.setCidade(rs.getString(EnderecoMap.cidade));
        endereco.setRua(rs.getString(EnderecoMap.rua));
        endereco.setId(rs.getString(EnderecoMap.id));
    }
}
