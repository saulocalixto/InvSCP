package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class RepositorioUsuario extends RepositorioPadrao<Usuario> implements IRepositorioUsuario {

    public Usuario Consultar(String id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                usuario.setNome(rs.getString(UsuarioMap.nome));
                usuario.setEmail(rs.getString(UsuarioMap.email));
                usuario.setCpf(rs.getString(UsuarioMap.cpf));
                usuario.setSenha(rs.getString(UsuarioMap.senha));
                usuario.setGrupo(rs.getString(UsuarioMap.grupo));
                usuario.setId(rs.getString(UsuarioMap.id));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return usuario;
    }

    public List<Usuario> ConsultarLista() {
        return null;
    }

    public void Salvar(Usuario objeto) {
        String sql = String.format("INSERT INTO %s(%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?)",
                UsuarioMap.nomeTabela,
                UsuarioMap.id,
                UsuarioMap.senha,
                UsuarioMap.nome,
                UsuarioMap.email,
                UsuarioMap.grupo,
                UsuarioMap.cpf);

        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, objeto.getId());
            stmt.setString(2, objeto.getSenha());
            stmt.setString(3, objeto.getNome());
            stmt.setString(4, objeto.getEmail());
            stmt.setString(5, objeto.getGrupo().name());
            stmt.setString(6, objeto.getCpf());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Atualizar(Usuario objeto) {
        Excluir(objeto.getId());
        Salvar(objeto);
    }

    public void Excluir(String id) {
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",
                UsuarioMap.nomeTabela,
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
    public Usuario consultarPorEmail(String email) {
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
                usuario.setGrupo(rs.getString(UsuarioMap.grupo));
                usuario.setId(rs.getString(UsuarioMap.id));
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return usuario;
    }

    @Override
    public Boolean usuarioNaoExiste(String email) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                UsuarioMap.id,
                UsuarioMap.nomeTabela,
                UsuarioMap.email,
                email);
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return false;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return true;
    }
}
