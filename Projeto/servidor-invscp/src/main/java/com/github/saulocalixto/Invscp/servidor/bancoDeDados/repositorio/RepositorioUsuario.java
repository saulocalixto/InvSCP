package com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.mapeadores.UsuarioMap;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.departamento.Departamento;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class RepositorioUsuario extends RepositorioPadrao<Usuario> implements IRepositorioUsuario {

    public Usuario Consultar(String id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        return ConsulteUsuario(id, sql);
    }

    public List<Usuario> ConsultarLista() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuario = new Usuario();
                PreencheUsuario(usuario, rs);
                listaUsuario.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return listaUsuario;
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
        String sql = String.format("UPDATE %s " +
                        "SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' " +
                        "WHERE %s = '%s'",
                UsuarioMap.nomeTabela,
                UsuarioMap.grupo,
                objeto.getGrupo(),
                UsuarioMap.nome,
                objeto.getNome(),
                UsuarioMap.email,
                objeto.getEmail(),
                UsuarioMap.cpf,
                objeto.getCpf(),
                UsuarioMap.senha,
                objeto.getSenha(),
                UsuarioMap.idDepartamento,
                objeto.getDepartamento().getId(),
                UsuarioMap.id,
                objeto.getId());
        try {
            ExecutaQuery(sql);
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void Excluir(String id) {
        ExcluirPadrao(UsuarioMap.nomeTabela, id);
    }

    @Override
    public Usuario consultarPorEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        return ConsulteUsuario(email, sql);
    }

    @Override
    public Boolean usuarioNaoExiste(String email) {
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                UsuarioMap.id,
                UsuarioMap.nomeTabela,
                UsuarioMap.email,
                email);
        return verificaSeNaoRetornaResultados(sql);
    }

    private void PreencheUsuario(Usuario usuario, ResultSet rs) throws SQLException {
        Departamento departamento = new Departamento();
        usuario.setNome(rs.getString(UsuarioMap.nome));
        usuario.setEmail(rs.getString(UsuarioMap.email));
        usuario.setCpf(rs.getString(UsuarioMap.cpf));
        usuario.setSenha(rs.getString(UsuarioMap.senha));
        usuario.setGrupo(rs.getString(UsuarioMap.grupo));
        usuario.setId(rs.getString(UsuarioMap.id));
        departamento.setId(rs.getString(UsuarioMap.idDepartamento));
        usuario.setDepartamento(departamento);
    }

    private Usuario ConsulteUsuario(String id, String sql) {
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = RetorneConexaoBd().prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                PreencheUsuario(usuario, rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            System.out.println("Erro de leitura.");;
        }
        return usuario;
    }
}
