package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.ConexaoBd;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
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
        String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
                UsuarioMap.nomeTabela,
                UsuarioMap.id,
                UsuarioMap.senha,
                UsuarioMap.nome,
                UsuarioMap.email,
                UsuarioMap.cpf);

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getSenha());
            stmt.setString(3, objeto.getNome());
            stmt.setString(4, objeto.getEmail());
            //stmt.setString(5, objeto.getGrupo().name());
            stmt.setString(5, objeto.getCpf());
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
    public Usuario ConsultarPorCpf(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                usuario.setNome(rs.getString(UsuarioMap.nome));
                usuario.setEmail(rs.getString(UsuarioMap.email));
                usuario.setCpf(rs.getString(UsuarioMap.cpf));
                usuario.setSenha(rs.getString(UsuarioMap.senha));
                usuario.setId(rs.getString(UsuarioMap.id));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return usuario;
    }
}
