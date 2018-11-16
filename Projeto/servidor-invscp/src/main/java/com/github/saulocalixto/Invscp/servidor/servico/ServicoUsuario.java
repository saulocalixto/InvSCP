package com.github.saulocalixto.Invscp.servidor.servico;

import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.RepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.bancoDeDados.repositorio.interfaces.IRepositorioUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.Usuario;
import com.github.saulocalixto.Invscp.servidor.negocio.usuario.ValidacoesUsuario;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.Inconsistencia;
import com.github.saulocalixto.Invscp.servidor.negocio.validacao.ValidadorPadrao;
import com.github.saulocalixto.Invscp.servidor.utilitarios.SenhaEncript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by Saulo Calixto on 23/10/18.
 */
public class ServicoUsuario implements IServico<Usuario> {

    private IRepositorioUsuario repositorio;
    private ValidadorPadrao<Usuario> validador;

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

    public List<Inconsistencia> Salvar(Usuario objeto) {
        validador = new ValidacoesUsuario(objeto);

        List<Inconsistencia> inconsistencias = validador.ValideInclusao();

        if(validador.ehValido()) {
            objeto.setSenha(SenhaEncript.criptografeSenha(objeto.getSenha()));
            repositorio().Salvar(objeto);
        }

        return inconsistencias;
    }

    public List<Inconsistencia> Atualizar(Usuario objeto) {

        validador = new ValidacoesUsuario(objeto);

        List<Inconsistencia> inconsistencias = validador.ValideAtualizacao();

        if(validador.ehValido()) {
            objeto.setSenha(SenhaEncript.criptografeSenha(objeto.getSenha()));
            repositorio().Atualizar(objeto);
        }

        return inconsistencias;
    }

    public void Excluir(String id) {
        // Criar Validação
        repositorio().Excluir(id);
    }

    private IRepositorioUsuario repositorio() {
        return repositorio != null ? repositorio : (repositorio = new RepositorioUsuario());
    }
}
