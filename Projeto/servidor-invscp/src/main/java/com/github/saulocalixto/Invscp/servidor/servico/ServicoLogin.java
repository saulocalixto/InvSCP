package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;

import java.util.List;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class ServicoLogin implements IServico<Login> {

    private IRepositorioUsuario repositorioUsuario;
    private IRepositorioLogin repositorioLogin;

    public Login validaLogin(String email, String senha) {
        Usuario usuario = repositorioUsuario().consultarPorEmail(email);
        Login login = new Login();
        if(usuario != null && usuario.getSenha().equals(senha)) {

            login.setUsuario(usuario);
            Login loginAntigo = repositorioLogin().retorneLoginUsuario(login.getUsuario().getId());

            if(loginAntigo.getUsuario() != null) {
                return loginAntigo;
            }

            repositorioLogin().Salvar(login);

            return login;
        }

        login.limpaTokenAcesso();
        return login;
    }

    public Boolean tokenValido(String token) {
        return repositorioLogin().tokenValido(token);
    }

    @Override
    public Login Consultar(String id) {
        return null;
    }

    @Override
    public List<Login> ConsultarLista() {
        return null;
    }

    @Override
    public List<Inconsistencia> Salvar(Login objeto) {
        return null;
    }

    @Override
    public void Atualizar(Login objeto) {

    }

    @Override
    public void Excluir(String id) {

    }

    private IRepositorioUsuario repositorioUsuario() {
        return repositorioUsuario != null ? repositorioUsuario : (repositorioUsuario = new RepositorioUsuario());
    }

    private IRepositorioLogin repositorioLogin() {
        return repositorioLogin != null ? repositorioLogin : (repositorioLogin = new RepositorioLogin());
    }
}
