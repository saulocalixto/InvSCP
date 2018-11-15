package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.Usuario;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ServicoUsuario implements IServico<Usuario> {

    private IRepositorioUsuario repositorio;

    public Usuario Consultar(String id) {
        return null;
    }

    public List<Usuario> ConsultarLista() {
        return null;
    }

    public Usuario consultarPorEmail(String email) {
        Usuario usuario = new Usuario();

        usuario = repositorio().consultarPorEmail(email);

        return usuario;
    }

    public void Salvar(Usuario objeto) {

        //valida objeto
        repositorio().Salvar(objeto);
    }

    public void Atualizar(Usuario objeto) {
        // Criar Validação
        repositorio().Atualizar(objeto);
    }

    public void Excluir(String id) {
        // Criar Validação
        repositorio().Excluir(id);
    }

    private IRepositorioUsuario repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioUsuario());
    }
}
