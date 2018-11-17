package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.LoginMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class RepositorioLogin implements IRepositorioLogin {

    private IRepositorioUsuario repositorioUsuario;

    @Override
    public void efetuarLogin(Login objeto) {

        String sql = String.format("INSERT INTO %s(%s,%s,%s) VALUES(?,?,?)",
                LoginMap.nomeTabela,
                LoginMap.id,
                LoginMap.token,
                LoginMap.idUsuario);

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getTokenAcesso());
            stmt.setString(3, objeto.getUsuario().getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    @Override
    public void deslogar(String tokeAcesso) {
        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                LoginMap.nomeTabela,
                LoginMap.token,
                tokeAcesso);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    @Override
    public Boolean tokenValido(String token) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                LoginMap.id,
                LoginMap.nomeTabela,
                LoginMap.token,
                token);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return false;
    }

    @Override
    public Login retorneLoginUsuario(String idUsuario) {
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'",
                LoginMap.nomeTabela,
                LoginMap.idUsuario,
                idUsuario);
        Login login = new Login();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                login.setUsuario(repositorioUsuario().Consultar(rs.getString(LoginMap.idUsuario)));
                login.setId(rs.getString(LoginMap.id));
                login.setTokenAcesso(rs.getString(LoginMap.token));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return login;
    }

    @Override
    public Usuario retorneUsuario(String token) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                LoginMap.idUsuario,
                LoginMap.nomeTabela,
                LoginMap.token,
                token);
        Login login = new Login();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                login.setUsuario(repositorioUsuario().Consultar(rs.getString(LoginMap.idUsuario)));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return login.getUsuario();
    }

    private IRepositorioUsuario repositorioUsuario() {
        return repositorioUsuario != null ? repositorioUsuario : (repositorioUsuario = new RepositorioUsuario());
    }

    private Connection RetorneConexaoBd() {
        return ConexaoBd.getConexaoMySQL();
    }
}
