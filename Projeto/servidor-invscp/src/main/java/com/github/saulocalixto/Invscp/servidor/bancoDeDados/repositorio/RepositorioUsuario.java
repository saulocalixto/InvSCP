package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorio;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class RepositorioUsuario extends RepositorioPadrao<Usuario> implements IRepositorioUsuario {

    public Usuario Consultar(String id) {
        return null;
    }

    public List<Usuario> ConsultarLista() {
        return null;
    }

    public void Salvar(Usuario objeto) {
        String sql = "INSERT INTO usuario(id, nomeUsuario, nome) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getNomeDeUsuario());
            stmt.setString(3, objeto.getCpf());
            stmt.setString(4, objeto.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Usuario objeto) {

    }

    public void Excluir(String id) {

    }

    @Override
    public String NomeTabela() {
        return "Usuario";
    }

    @Override
    public Usuario ConsultarPorCpf(String cpf) {
        String sql = "SELECT * FROM aluno WHERE cpf = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                //etc e tal...
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return usuario;
    }
}
