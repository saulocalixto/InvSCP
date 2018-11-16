package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioLogin;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.Login;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.utilitarios.SenhaEncript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * Created by Saulo Calixto on 13/11/18.
 */
public class ServicoLogin {

    private IRepositorioUsuario repositorioUsuario;
    private IRepositorioLogin repositorioLogin;

    public Login validaLogin(String email, String senha) {
        Usuario usuario = repositorioUsuario().consultarPorEmail(email);
        Login login = new Login();
        if(usuario != null && SenhaEncript.valideSenhe(usuario.getSenha(), senha)) {

            login.setUsuario(usuario);
            Login loginAntigo = repositorioLogin().retorneLoginUsuario(login.getUsuario().getId());

            if(loginAntigo.getUsuario() != null) {
                return loginAntigo;
            }

            repositorioLogin().efetuarLogin(login);

            return login;
        }

        login.limpaTokenAcesso();
        return login;
    }

    public void deslogar(String tokenAcesso) {
        repositorioLogin().deslogar(tokenAcesso);
    }

    public Boolean tokenValido(String token) {
        return repositorioLogin().tokenValido(token);
    }

    private IRepositorioUsuario repositorioUsuario() {
        return repositorioUsuario != null ? repositorioUsuario : (repositorioUsuario = new RepositorioUsuario());
    }

    private IRepositorioLogin repositorioLogin() {
        return repositorioLogin != null ? repositorioLogin : (repositorioLogin = new RepositorioLogin());
    }
}
